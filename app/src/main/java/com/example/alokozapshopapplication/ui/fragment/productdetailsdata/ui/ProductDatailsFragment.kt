package com.example.alokozapshopapplication.ui.fragment.productdetailsdata.ui


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentProductDatailsBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartItemResponse
import com.example.alokozapshopapplication.ui.fragment.descriptionfragment.ui.DescriptionFragment
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.ingrediants.ui.IngrediantsFragment
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.`interface`.ProductDetailsFragmentInterface
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.adapter.ReletedProductListAdapter
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.adapter.UpSellProductListAdapter
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.adapter.ViewPagerItemAdapter
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.viewmodel.ProductDetailsViewModel
import com.example.alokozapshopapplication.ui.fragment.reviewsfragment.ui.ReviewsFragment
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


class ProductDatailsFragment :
    FragmentBase<ProductDetailsViewModel, FragmentProductDatailsBinding>() {

    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    private var imageList = ArrayList<ProductDetailsResponse.ImageGallery>()
    private var productDetailModel: ProductDetailsResponse? = ProductDetailsResponse()
    private lateinit var viewPagerItemAdapter: ViewPagerItemAdapter
    private val args: ProductDatailsFragmentArgs by navArgs()
    private var timer: Timer? = null
    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")

    private lateinit var reletedProductListAdapter: ReletedProductListAdapter
    private lateinit var upSellProductListAdapter: UpSellProductListAdapter
    private var reletedProductList = ArrayList<ProductDetailsResponse.RelatedProduct>()
    private var upSellProductList = ArrayList<ProductDetailsResponse.UpsellProduct>()


    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = true,
            imageArrow = false,
            imageSearch = false,
            true


        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val entityId = args.itemId.toString()
        productDetailsViewModel.productPageData(
            customerToken = customerToken.toString(),
            quoteId = quoteId.toString(),
            productId = entityId
        )
        setLiveDataObservers()
    }

    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner = viewLifecycleOwner
        val entityId = args.itemId.toString()
        getDataBinding().cvAddToCart.setOnClickListener {
            productDetailsViewModel.getAddToCart(
                customerToken.toString(),
                productId = entityId,
                quoteId.toString()
            )
        }

        getDataBinding().ivHeart.setOnClickListener {
            productDetailsViewModel.addWishList(customerToken.toString(), productId = entityId)
        }

        reletedProductListAdapter()
        upSellProductListAdapter()
    }


    override fun getLayoutId(): Int = R.layout.fragment_product_datails

    private fun setLiveDataObservers() {


        productDetailsViewModel.removeWishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    productDetailsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    productDetailsViewModel.showProgressBar(false)
                    state.message.let { productDetailsViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<RemoveWishListResponse> -> {
                    productDetailsViewModel.showProgressBar(false)

                    if (state.response != null) {
                        state.response.message.let {
                            if (it != null) {
                                productDetailsViewModel.showSnackbarMessage(it)
                            }
                        }
                    }
                }
            }
        }

        productDetailsViewModel.addWishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    productDetailsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    productDetailsViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                    state.message.let { productDetailsViewModel.showSnackbarMessage(it) }
                }
                is ResponseHandler.OnSuccessResponse<AddWishListResponse> -> {
                    productDetailsViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        state.response.message?.let { productDetailsViewModel.showSnackbarMessage(it) }
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


        productDetailsViewModel.productDetailsResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    productDetailsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    productDetailsViewModel.showProgressBar(false)
                    state.message.let { productDetailsViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<ProductDetailsResponse> -> {
                    productDetailsViewModel.showProgressBar(false)
                    getDataBinding().productDetailsModel = state.response
                    productDetailModel = state.response
                    state.response?.imageGallery?.let { it1 -> imageList.addAll(it1) }


                    if(state.response?.relatedProductList?.isEmpty() == true){
                        getDataBinding().tvCategoryItemName.isVisible=false

                    }else{
                        reletedProductList.clear()
                        reletedProductList.addAll(state.response?.relatedProductList as ArrayList<ProductDetailsResponse.RelatedProduct>)
                        reletedProductListAdapter.updateCategoryItemData(reletedProductList)
                    }


                    if(state.response.upsellProductList?.isEmpty() == true){
                        getDataBinding().tvCategoryUpSellProduct.isVisible=false

                    }else{
                        upSellProductList.clear()
                        upSellProductList.addAll(state.response.upsellProductList as ArrayList<ProductDetailsResponse.UpsellProduct>)
                        upSellProductListAdapter.updateCategoryItemData(upSellProductList)
                    }




                    viewPagerSlider()
                    viewPagerItemSlider()
                    reletedProductListAdapter()
                    upSellProductListAdapter()

                }
            }
        }

        productDetailsViewModel.addToCartResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    productDetailsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    productDetailsViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<AddToCartResponse> -> {
                    productDetailsViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        state.response.message?.let { productDetailsViewModel.showSnackbarMessage(it) }
                    }
                }
            }

        }
    }


    override fun getViewModel(): ProductDetailsViewModel {
        productDetailsViewModel = ViewModelProvider(this)[ProductDetailsViewModel::class.java]
        return productDetailsViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }


    private fun viewPagerItemSlider() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, DescriptionFragment(productDetailModel))
            .commit()

        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Description")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Ingredients")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Reviews")
        )
        getDataBinding().tlCategoryItem.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.container, DescriptionFragment(productDetailModel))
                            .commit()
                    }
                    1 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.container, IngrediantsFragment(productDetailModel))
                            .commit()
                    }
                    2 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.container, ReviewsFragment(productDetailModel))
                            .commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }


    private fun viewPagerSlider() {
        viewPagerItemAdapter = ViewPagerItemAdapter(requireActivity(), imageList)
        getDataBinding().vpSlider.adapter = viewPagerItemAdapter

        val DELAY_MS: Long = 3000
        val PERIOD_MS: Long = 3000

        val handler = Handler(Looper.getMainLooper())

        val update = Runnable {
            if (getDataBinding().vpSlider.currentItem == viewPagerItemAdapter.itemCount - 1) {
                getDataBinding().vpSlider.currentItem = 0
            } else {
                getDataBinding().vpSlider.setCurrentItem(
                    getDataBinding().vpSlider.currentItem + 1,
                    true
                )
            }
        }
        timer?.cancel()
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)

        getDataBinding().tab.removeAllTabs()
        TabLayoutMediator(
            getDataBinding().tab,
            getDataBinding().vpSlider
        ) { tab, position -> }.attach()
    }


    private fun reletedProductListAdapter() {
        reletedProductListAdapter =
            ReletedProductListAdapter(requireContext(), object : ProductDetailsFragmentInterface {
                override fun onAddWishList(
                    position: Int,
                    view: View,
                    data: AddToCartItemResponse.Item
                ) {

                }

                override fun onDeleteWishList(
                    position: Int,
                    view: View,
                    data: AddToCartItemResponse.Item
                ) {

                }

                override fun onProductRecent(
                    position: Int,
                    view: View,
                    data: ProductDetailsResponse.RelatedProduct
                ) {

                    val action=ProductDatailsFragmentDirections.actionProductDatailsFragmentSelf(data.entityId.toString())
                    findNavController().navigate(action)

                }

                override fun onProductUpSell(
                    position: Int,
                    view: View,
                    data: ProductDetailsResponse.UpsellProduct
                ) {

                }

            }, reletedProductList)

        getDataBinding().rvReletedProductList.adapter = reletedProductListAdapter
    }

    private fun upSellProductListAdapter() {
        upSellProductListAdapter =
            UpSellProductListAdapter(requireContext(), object : ProductDetailsFragmentInterface {
                override fun onAddWishList(
                    position: Int,
                    view: View,
                    data: AddToCartItemResponse.Item
                ) {

                }

                override fun onDeleteWishList(
                    position: Int,
                    view: View,
                    data: AddToCartItemResponse.Item
                ) {

                }

                override fun onProductRecent(
                    position: Int,
                    view: View,
                    data: ProductDetailsResponse.RelatedProduct
                ) {
                    val action=ProductDatailsFragmentDirections.actionProductDatailsFragmentSelf(data.entityId.toString())
                    findNavController().navigate(action)
                }

                override fun onProductUpSell(
                    position: Int,
                    view: View,
                    data: ProductDetailsResponse.UpsellProduct
                ) {
                    val action=ProductDatailsFragmentDirections.actionProductDatailsFragmentSelf(data.entityId.toString())
                    findNavController().navigate(action)
                }

            }, upSellProductList)

        getDataBinding().rvUpsellProductList.adapter = upSellProductListAdapter
    }


}


//region start ProductDetailsFragment
/*class ProductDatailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDatailsBinding

    private lateinit var productDetailsViewPagerAdapter: ProductDetailsViewPagerAdapter

    private lateinit var productDetailsViewModel: ProductDetailsViewModel
    lateinit var sh: SharedPreferences
    private var imageList = ArrayList<ProductDetailsResponse.ImageGallery>()
    private var productDetailsModel: ProductDetailsResponse? = ProductDetailsResponse()

    private lateinit var viewPagerItemAdapter: ViewPagerItemAdapter
    private val args: ProductDatailsFragmentArgs by navArgs()

    private var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        sh = requireActivity().getSharedPreferences("SignUp_Data", AppCompatActivity.MODE_PRIVATE)
        val productDetailsRepository = ProductDetailsRepository()

        productDetailsViewModel = ViewModelProvider(
            this, ProductDetailsViewModelFactory(productDetailsRepository)
        )[ProductDetailsViewModel::class.java]


        val entityId = args.itemId.toString()

        val customerToken = sh.getString("token", "")
        val quoteId = sh.getString("quoteId", "")

        Log.d("entityId", "onCreate: $entityId")
        productDetailsViewModel.productPageData(
            customerToken = customerToken.toString(), quoteId = quoteId.toString(),
            productId = entityId
        )


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_product_datails, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        //binding.viewPager.isUserInputEnabled = false

        binding.tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }
//region tableViewPager

        binding.tlCategoryItem.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tab.getTabAt(position)?.select()
            }

        })


//endregion
        productDetailsViewModel.productDetailsResponseLiveData.observe(viewLifecycleOwner) {

            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {
                    binding.pbProgressBar.visibility = View.GONE
                    binding.productDetailsModel = it.data
                    productDetailsModel = it.data




                    imageList.clear()

                    it.data?.imageGallery?.let { it1 -> imageList.addAll(it1) }

                    viewPagerSlider()
                    viewPagerItemSlider()


                }
                is BaseResponse.Error -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                else -> {
                    binding.pbProgressBar.visibility = View.GONE
                }
            }
        }

        viewPagerSlider()
        viewPagerItemSlider()


        binding.tbToolbar.etSearch.setOnClickListener {
            val action =
                ProductDatailsFragmentDirections.actionProductDatailsFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun viewPagerSlider() {
        viewPagerItemAdapter = ViewPagerItemAdapter(requireActivity(), imageList)
        binding.vpSlider.adapter = viewPagerItemAdapter

        val DELAY_MS: Long = 3000
        val PERIOD_MS: Long = 3000

        val handler = Handler(Looper.getMainLooper())

        val update = Runnable {
            if (binding.vpSlider.currentItem == viewPagerItemAdapter.itemCount - 1) {
                binding.vpSlider.currentItem = 0
            } else {
                binding.vpSlider.setCurrentItem(binding.vpSlider.currentItem + 1, true)
            }
        }
        timer?.cancel()
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)

        binding.tab.removeAllTabs();


        TabLayoutMediator(binding.tab, binding.vpSlider) { tab, position -> }.attach()
    }

    private fun viewPagerItemSlider() {
        childFragmentManager.beginTransaction()
            .replace(R.id.container, DescriptionFragment(productDetailsModel))
            .commit()
        //val adapter = activity?.let { ProductDetailsViewPagerAdapter(it, productDetailsModel) }
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Description"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Ingredients"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Reviews"))
        binding.tlCategoryItem.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.container, DescriptionFragment(productDetailsModel))
                            .commit()
                    }
                    1 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.container, IngrediantsFragment(productDetailsModel))
                            .commit()
                    }
                    2 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.container, ReviewsFragment(productDetailsModel))
                            .commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tlCategoryItem, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Description"
                }
                1 -> {
                    tab.text = "Ingredients"
                }
                2 -> {
                    tab.text = "Reviews"
                }
            }

        }.attach()

    }


}*/
//endregion