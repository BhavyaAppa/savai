package com.example.alokozapshopapplication.ui.fragment.mywishlist.ui


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentMyWishListBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.`interface`.WishListInterface
import com.example.alokozapshopapplication.ui.fragment.mywishlist.adapter.WishListAdapter
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.getwishlist.WishListResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.viewmodel.WishListViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey

class MyWishListFragment : FragmentBase<WishListViewModel, FragmentMyWishListBinding>() {

    private lateinit var wishListViewModel: WishListViewModel
    private lateinit var wishListAdapter: WishListAdapter
    private var list = ArrayList<WishListResponse.Wish>()
    private var selectedItem: WishListResponse.Wish? = null
    private var selectPosition: WishListResponse.Wish? = null
    private var removeList = ArrayList<RemoveWishListResponse>()

    private var isLoading: Boolean = true
    private var pageNumber = 1
    private var response: WishListResponse? = null
    private var removeResponse: RemoveWishListResponse? = null


    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_wish_list

    }

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = false,
            imageArrow = true,
            imageSearch = false,
            true
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wishListViewModel.wishList(
            customerToken = customerToken.toString(),
            pageNumber
        )
        setLiveDataObservers()
    }

    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner = viewLifecycleOwner

        wishListAdapter()
    }

    override fun getViewModel(): WishListViewModel {
        wishListViewModel = ViewModelProvider(this)[WishListViewModel::class.java]
        return wishListViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setLiveDataObservers() {
        list.clear()

        wishListViewModel.wishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    wishListViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    wishListViewModel.showProgressBar(false)
                    state.message.let { wishListViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<WishListResponse> -> {
                    wishListViewModel.showProgressBar(false)
                    isLoading = false
                    response = state.response
                    if (state.response?.wishList?.isEmpty() == true) {
                        getDataBinding().clEmptyWishList.visibility = View.VISIBLE
                        getDataBinding().clWishList.visibility = View.GONE
                        wishListAdapter.notifyDataSetChanged()
                    } else {
                        list.addAll(state.response?.wishList as ArrayList<WishListResponse.Wish>)
                        wishListAdapter.notifyDataSetChanged()

                    }
                }
            }
        }

        wishListViewModel.addToCartResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    wishListViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    wishListViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<AddToCartResponse> -> {
                    wishListViewModel.showProgressBar(false)
                    if (state.response?.success == true) {

                        list.remove(selectPosition)
                        wishListAdapter.notifyDataSetChanged()
                        state.response.message?.let { wishListViewModel.showSnackbarMessage(it) }
                    }
                }
            }

        }

        wishListViewModel.removeWishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    wishListViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    wishListViewModel.showProgressBar(false)
                    state.message.let { wishListViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<RemoveWishListResponse> -> {
                    wishListViewModel.showProgressBar(false)

                    if (state.response != null) {
                        removeResponse = state.response
                        var pos=list.size
                        list.remove(selectedItem)
                        var oldPos=list.size
                        wishListAdapter.notifyItemRangeChanged(pos,oldPos)
                        state.response.message.let {
                            if (it != null) {
                                wishListViewModel.showSnackbarMessage(it)
                            }
                        }
                    }
                }
            }
        }

    }


    private fun wishListAdapter() {
        wishListAdapter = WishListAdapter(requireContext(), object : WishListInterface {
            override fun onItemDelete(position: Int, view: View, data: WishListResponse.Wish) {

                selectedItem = data
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("AlokozayShop")
                builder.setMessage("Do You want to delete this item from wishlist?")
                builder.setCancelable(false)
                    .setPositiveButton("yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            wishListViewModel.removeWishList(
                                customerToken = customerToken.toString(),
                                itemId = data.wishlistItemId.toString()
                            )
                        })
                    .setNegativeButton(R.string.no,
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.dismiss()

                        })

                val dialog = builder.create()
                dialog.show()

                val btnPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
                val btnNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                with(btnPositive) {
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_dark_main))
                }
                with(btnNegative) {
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_dark_main))
                }


            }

            override fun onAddCard(
                position: Int,
                view: View,
                data: WishListResponse.Wish,
                count: Int
            ) {
                selectPosition = data

                wishListViewModel.getAddToCart(
                    customerToken.toString(),
                    data.entityId.toString(),
                    quoteId.toString(),
                )
            }

            override fun onProductDetails(position: Int, view: View, data: WishListResponse.Wish) {
                val action =
                    MyWishListFragmentDirections.actionMyWishListFragmentToProductDatailsFragment(
                        data.productId.toString()
                    )
                findNavController().navigate(action)
            }

        }, list)
        getDataBinding().rvRecyclarView.adapter = wishListAdapter


        getDataBinding().rvRecyclarView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (list.size < 10) return
                val lastCompletelyVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!isLoading && list.size < (response?.totalCount?.toInt()
                        ?: 0) && lastCompletelyVisibleItemPosition > list.size - 4
                ) {
                    isLoading = true
                    wishListViewModel.wishList(
                        customerToken = customerToken.toString(),
                        ++pageNumber
                    )
                }
            }
        })
    }

}
