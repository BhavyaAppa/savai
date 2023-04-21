package com.example.alokozapshopapplication.ui.fragment.cartfragment.ui

import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentCartBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.cartfragment.`interface`.CartFragmentInterface
import com.example.alokozapshopapplication.ui.fragment.cartfragment.adapter.AddToCartDetailsAdapter
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartItemResponse
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.emptycart.EmptyCartResponce
import com.example.alokozapshopapplication.ui.fragment.cartfragment.viewmodel.AddToCartDetailsViewModel
import com.example.alokozapshopapplication.ui.fragment.homefragment.ui.HomeFragmentDirections
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey



class CartFragment : FragmentBase<AddToCartDetailsViewModel, FragmentCartBinding>() {


    private lateinit var addToCartDetailsViewModel: AddToCartDetailsViewModel
    private lateinit var adapter: AddToCartDetailsAdapter
    private var addCartItemList = ArrayList<AddToCartItemResponse.Item>()
    private var dateList = java.util.ArrayList<AddToCartItemResponse>()
    private var response = AddToCartItemResponse()
    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")


    override fun getLayoutId(): Int = R.layout.fragment_cart

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = false,
            imageArrow = false,
            imageSearch = true,
            true


        )
    }

    override fun initializeScreenVariables() {


        getDataBinding().btnProceedBuy.setOnClickListener {
            val action = CartFragmentDirections.actionMnCartToTimeSlotFragment()
            findNavController().navigate(action)
        }

        addToCartDetailsViewModel.getAddToCartDetails(
            customerToken = customerToken.toString(),
            quoteId = quoteId.toString()
        )

        addToCartAdapter()
        setupListener()
        setData()
        setLiveDataObservers()




    }

    private fun setLiveDataObservers() {



        addToCartDetailsViewModel.removeWishListResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    addToCartDetailsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    addToCartDetailsViewModel.showProgressBar(false)
                    state.message.let { addToCartDetailsViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<RemoveWishListResponse> -> {
                    addToCartDetailsViewModel.showProgressBar(false)

                    if (state.response != null) {
                        state.response.message.let {
                            if (it != null) {
                                addToCartDetailsViewModel.showSnackbarMessage(it)
                            }
                        }
                    }
                }
            }
        }

        addToCartDetailsViewModel.addWishListResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    addToCartDetailsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    addToCartDetailsViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                    state.message.let { addToCartDetailsViewModel.showSnackbarMessage(it) }
                }
                is ResponseHandler.OnSuccessResponse<AddWishListResponse> -> {
                    addToCartDetailsViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        state.response.message?.let { addToCartDetailsViewModel.showSnackbarMessage(it) }
                        state.response.itemId.let {
                            state.let { it1 ->
                                MyPreference.setValueString(
                                    PrefKey.WISH_LIST,
                                    it1.toString()
                                )
                            }
                        }

                    }
                }
            }

        }


        addToCartDetailsViewModel.addToCartDetailsResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    addToCartDetailsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    addToCartDetailsViewModel.showProgressBar(false)

                    state.message.let { addToCartDetailsViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<AddToCartItemResponse> -> {
                    addToCartDetailsViewModel.showProgressBar(false)

                    if (state.response?.items?.isNotEmpty() == true) {
                        getDataBinding().nsMainCart.visibility = View.VISIBLE
                        getDataBinding().cvProceedBuy.visibility = View.VISIBLE
                        response = state.response
                        setData()
                        addCartItemList.clear()
                        dateList.addAll(listOf(state.response))
                        addCartItemList.addAll(state.response.items)
                        addToCartAdapter()
                    }else{
                        getDataBinding().clEmptyCart.visibility = View.VISIBLE
                    }
                }
            }
        }








        addToCartDetailsViewModel.emptyCartResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    addToCartDetailsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    addToCartDetailsViewModel.showProgressBar(false)
                    state.message.let { addToCartDetailsViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<EmptyCartResponce> -> {
                    addToCartDetailsViewModel.showProgressBar(false)

                    if (state.response == null ) {
                        if (getDataBinding().nsMainCart.visibility == View.VISIBLE
                            && getDataBinding().cvProceedBuy.visibility == View.VISIBLE
                        ) {
                            getDataBinding().nsMainCart.visibility = View.GONE
                            getDataBinding().cvProceedBuy.visibility = View.GONE
                            getDataBinding().clEmptyCart.visibility = View.VISIBLE
                            addToCartDetailsViewModel.showSnackbarMessage("Cart Empty Sucessfully")

                        } else {
                            getDataBinding().clMainCart.visibility = View.VISIBLE
                            getDataBinding().clEmptyCart.visibility = View.GONE
                            getDataBinding().cvProceedBuy.visibility = View.VISIBLE

                        }
                    } else {

                        getDataBinding().clMainCart.visibility = View.GONE
                        getDataBinding().clEmptyCart.visibility = View.VISIBLE
                        getDataBinding().cvProceedBuy.visibility = View.GONE


                    }

                }

            }
        }
    }


    override fun getViewModel(): AddToCartDetailsViewModel {
        addToCartDetailsViewModel = ViewModelProvider(this)[AddToCartDetailsViewModel::class.java]
        return addToCartDetailsViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }


    private fun setData() {
        getDataBinding().response = response
    }

    private fun addToCartAdapter() {
        adapter = AddToCartDetailsAdapter(requireContext(),object : CartFragmentInterface{
            override fun onAddWishList(
                position: Int,
                view: View,
                data: AddToCartItemResponse.Item
            ) {

                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("AlokozayShop")
                builder.setMessage("This item will be moved to your wish list.")
                builder.setCancelable(false)
                    .setPositiveButton("yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            addToCartDetailsViewModel.addWishList(
                                customerToken.toString(),
                                data.productId.toString()
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

            override fun onDeleteWishList(
                position: Int,
                view: View,
                data: AddToCartItemResponse.Item
            ) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("AlokozayShop")
                builder.setMessage("This item will be removed to your wish list.")
                builder.setCancelable(false)
                    .setPositiveButton("yes",
                        DialogInterface.OnClickListener { dialog, id ->
                            addToCartDetailsViewModel.removeWishList(
                                customerToken = customerToken.toString(),
                                itemId = data.productId.toString()
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

            override fun onWishListProductDetails(
                position: Int,
                view: View,
                data: AddToCartItemResponse.Item
            ) {
                val action =
                    CartFragmentDirections.actionMnCartToProductDatailsFragment(data.productId.toString())
                findNavController().navigate(action)
            }
        },addCartItemList)
        adapter.updateAddToCartItemData(addCartItemList)
        getDataBinding().rvAddCartItem.adapter = adapter
    }

    private fun setupListener() {

        getDataBinding().cvEmptyShoppingCart.setOnClickListener {


            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("AlokozayShop")
            builder.setMessage("Are you sure you want to Remove carts ?")
            builder.setCancelable(false)
                .setPositiveButton("yes,empty it",
                    DialogInterface.OnClickListener { dialog, id ->
                        addToCartDetailsViewModel.getAddToCartEmpty(
                            customerToken = customerToken.toString(),
                            quoteId = quoteId.toString()
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
    }


}

//region start cartDetails
/*
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var addToCartDetailsViewModel: AddToCartDetailsViewModel
    private lateinit var adapter: AddToCartDetailsAdapter
    private var addCartItemList = ArrayList<AddToCartItemResponse.Item>()
    private var dateList = java.util.ArrayList<AddToCartItemResponse>()
    private var response = AddToCartItemResponse()


    lateinit var sh: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val addToCartDetailsRepository = AddToCartDetailsRepository()

        addToCartDetailsViewModel = ViewModelProvider(
            this, AddToCartDetailsViewModelFactory(addToCartDetailsRepository)
        )[AddToCartDetailsViewModel::class.java]
        sh = requireActivity().getSharedPreferences("SignUp_Data", AppCompatActivity.MODE_PRIVATE)


        val customerToken = sh.getString("token", "")
        Log.d("customerToken", "onCreate: $customerToken")
        val quoteId = sh.getString("quoteId", "")
        addToCartDetailsViewModel.getAddToCartDetails(
            customerToken = customerToken.toString(),
            quoteId = quoteId.toString()
        )

        addToCartDetailsViewModel.addToCartDetailsResponseLiveData.observe(this) {

            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {
                    binding.pbProgressBar.visibility = View.GONE


                    if (it.data?.success == true) {

                        if (it.data.items != null ) {
                            binding.nsMainCart.visibility = View.VISIBLE
                            binding.cvProceedBuy.visibility = View.VISIBLE
                            binding.clEmptyCart.visibility = View.GONE
                            response = it.data
                            setData()
                            addCartItemList.clear()
                            dateList.addAll(listOf(it.data))
                            addCartItemList.addAll(it.data.items as java.util.ArrayList<AddToCartItemResponse.Item>)
                            addToCartAdapter()
                            Log.e("Category Data", "onCreateView: $it")
                        }else{

                            if(it.data.timeslotDetails  != null && it.data.freeGiftMsg != null ){

                                it.data.timeslotDetails = null
                                it.data.freeGiftMsg = null

                                binding.nsMainCart.visibility = View.GONE
                                binding.cvProceedBuy.visibility = View.GONE
                                binding.clEmptyCart.visibility = View.VISIBLE
                            }

                        }

                    }
                }
                is BaseResponse.Error -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                else -> {
                    binding.pbProgressBar.visibility = View.GONE
                }
            }
        }


        addToCartDetailsViewModel.addToCartEmptyResponseLiveData.observe(this) {

            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {
                    binding.pbProgressBar.visibility = View.GONE

                    if (it.data?.success == true) {



                        if (binding.nsMainCart.visibility == View.VISIBLE
                            && binding.cvProceedBuy.visibility == View.VISIBLE
                        ) {
                            binding.nsMainCart.visibility = View.GONE
                            binding.cvProceedBuy.visibility = View.GONE
                            binding.clEmptyCart.visibility = View.VISIBLE
                            Toast.makeText(
                                requireContext(),
                                "Cart Empty Sucessfully",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            binding.clMainCart.visibility = View.VISIBLE
                            binding.clEmptyCart.visibility = View.GONE
                            binding.cvProceedBuy.visibility = View.VISIBLE

                        }
                    } else {

                        binding.clMainCart.visibility = View.GONE
                        binding.clEmptyCart.visibility = View.VISIBLE
                        binding.cvProceedBuy.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Data Not Found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
                is BaseResponse.Error -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                else -> {
                    binding.pbProgressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setData() {
        binding.response = response
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnProceedBuy.setOnClickListener {
            val action = CartFragmentDirections.actionMnCartToTimeSlotFragment(
*/
/*                date = dateList[0].timeslotDetails?.orderDeliveryDate,
                time = dateList[0].timeslotDetails?.orderDeliveryTime,*//*

            )
            findNavController().navigate(action)
        }

        addToCartAdapter()
        setupListener()
        setData()
        binding.tbToolbar.etSearch.setOnClickListener {
            val action= CartFragmentDirections.actionMnCartToSearchFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }


    private fun addToCartAdapter() {
        adapter = AddToCartDetailsAdapter(requireContext(), addCartItemList)
        adapter.updateAddToCartItemData(addCartItemList)
        binding.rvAddCartItem.adapter = adapter
    }

    private fun setupListener() {

        binding.cvEmptyShoppingCart.setOnClickListener {


            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("AlokozayShop")
            builder.setMessage("Are you sure you want to Remove carts ?")
            builder.setCancelable(false)
                .setPositiveButton("yes,empty it",
                    DialogInterface.OnClickListener { dialog, id ->
                        val customerToken = sh.getString("token", "")
                        Log.d("customerToken", "onCreate: $customerToken")
                        val quoteId = sh.getString("quoteId", "")
                        addToCartDetailsViewModel.getAddToCartEmpty(
                            customerToken = customerToken.toString(),
                            quoteId = quoteId.toString()
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
    }


}*/

//endregion
