package com.example.alokozapshopapplication.ui.fragment.dealsfragment.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentDealsBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.bottomsheet.ui.BottomSheetFragment
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.`interface`.DealsFragmentInterface
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.adapter.DealsCategoryAdapter
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.deals.DealsResponse
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.viewmodel.DealsViewModel
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey

class DealsFragment : FragmentBase<DealsViewModel, FragmentDealsBinding>() {

    private lateinit var dealsViewModel: DealsViewModel

    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")

    private lateinit var adapter: DealsCategoryAdapter
    private var dealsData = ArrayList<DealsResponse.Carousel.Product>()
    private var list = ArrayList<DealsResponse.Carousel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dealsViewModel.getDealsData(customerToken.toString())
        setLiveDataObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setLiveDataObservers() {
        dealsViewModel.removeWishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    dealsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    dealsViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<RemoveWishListResponse> -> {
                    dealsViewModel.showProgressBar(false)
                    state.response?.message?.let { dealsViewModel.showSnackbarMessage("Item sucessfully deleted from wishlist") }

                }
            }
        }

        dealsViewModel.addWishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    dealsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    dealsViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)

                }
                is ResponseHandler.OnSuccessResponse<AddWishListResponse> -> {
                    dealsViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        state.response.message?.let { dealsViewModel.showSnackbarMessage("Item added to wishlist Sucessfully") }
                    }
                }
            }

        }

        dealsViewModel.dealsResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    dealsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    dealsViewModel.showProgressBar(false)
                    MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)
                    state.message.let { dealsViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<DealsResponse> -> {
                    dealsViewModel.showProgressBar(false)

                    if (state.response?.carousel != null) {
                        for (list in state.response.carousel){
                            if (list?.id?.contains("hotDeals") == true){
                                dealsData.clear()
                                list.productList?.forEach {
                                    dealsData.addAll(listOf(it))
                                    adapter.notifyDataSetChanged()
                                    Log.d("DealsData", "DealsData:$list")
                                }
                            }
                        }
                    }

                }
            }
        }
        dealsViewModel.addToCartResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    dealsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    dealsViewModel.showProgressBar(false)
                    MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<AddToCartResponse> -> {
                    dealsViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        state.response.message?.let { dealsViewModel.showSnackbarMessage(it) }
                        state.response.itemId.let {
                            state.let { it1 ->
                                MyPreference.setValueString(
                                    PrefKey.ITEM_ID,
                                    it1.toString()
                                )
                            }
                        }

                        state.response.quoteId.let {
                            it?.let { it1 ->
                                MyPreference.setValueString(
                                    PrefKey.QUOTE_ID,
                                    it1
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun dealsCategoryAdapter() {
        adapter = DealsCategoryAdapter(
            requireContext(),
            object : DealsFragmentInterface {
                override fun onProductDetail(
                    position: Int,
                    view: View,
                    data: DealsResponse.Carousel.Product
                ) {
                    val action =
                        DealsFragmentDirections.actionMnDealsToProductDatailsFragment(data.entityId)
                    findNavController().navigate(action)
                }

                override fun onRemoveCart(
                    position: Int,
                    view: View,
                    data: DealsResponse.Carousel.Product,
                    count: Int
                ) {

                }

                override fun onAddCard(
                    position: Int,
                    view: View,
                    data: DealsResponse.Carousel.Product,
                    count: Int
                ) {
                    dealsViewModel.getAddToCart(
                        customerToken.toString(),
                        data.entityId.toString(),
                        quoteId.toString(),
                    )
                }

                override fun onAddWishList(
                    position: Int,
                    view: View,
                    data: DealsResponse.Carousel.Product
                ) {
                    dealsViewModel.addWishList(
                        customerToken.toString(),
                        data.entityId.toString()
                    )
                }

                override fun onRemoveWishList(
                    position: Int,
                    view: View,
                    data: DealsResponse.Carousel.Product
                ) {
                    dealsViewModel.removeWishList(
                        customerToken = customerToken.toString(),
                        itemId = data.wishlistItemId.toString()
                    )
                }
            }, dealsData
        )

        getDataBinding().rvRecyclarView.adapter = adapter
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_deals

    }

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = true,
            imageArrow = false,
            imageSearch = false,
            true
        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().dealsViewModel = dealsViewModel
        getDataBinding().lifecycleOwner = viewLifecycleOwner
        dealsCategoryAdapter()


        getDataBinding().cvSort.setOnClickListener {
            val sortBottomSheetFragment = BottomSheetFragment()
            sortBottomSheetFragment.show(parentFragmentManager, sortBottomSheetFragment.tag)
        }




    }

    override fun getViewModel(): DealsViewModel {
        dealsViewModel = ViewModelProvider(this)[DealsViewModel::class.java]
        return dealsViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

}


