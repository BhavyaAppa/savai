package com.example.alokozapshopapplication.ui.fragment.homefragment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentHomeBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.homefragment.`interface`.HomeFragmentItemClickInterface
import com.example.alokozapshopapplication.ui.fragment.homefragment.adapter.CategoryAdapter
import com.example.alokozapshopapplication.ui.fragment.homefragment.adapter.ParentSubCategoryAdapter
import com.example.alokozapshopapplication.ui.fragment.homefragment.adapter.ViewPagerAdapter
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homeviewmodel.HomeViewModel
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class HomeFragment : FragmentBase<HomeViewModel, FragmentHomeBinding>() {
    private lateinit var homeViewModel: HomeViewModel


    private lateinit var adapter: CategoryAdapter
    private var list = ArrayList<HomeResponse.FeaturedCategory>()
    private var bannerList = ArrayList<HomeResponse.Carousel.Banner>()

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var parentSubCategoryAdapter: ParentSubCategoryAdapter

    private var parentDataList = ArrayList<HomeResponse.Carousel>()
    private var parentDataListFeature = ArrayList<HomeResponse.FeaturedCategory>()
    private var childDataList = ArrayList<HomeResponse.Carousel.Product?>()


    private var timer: Timer? = null
    private var wishList: String? = null

    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = false,
            imageArrow = false,
            imageSearch = true,
            true


        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getHomeData(customerToken.toString())
        setLiveDataObservers()
    }

    override fun initializeScreenVariables() {
        getDataBinding().homeViewModel = homeViewModel
        subCategory()
        categoryItemClick()
        viewPagerSlider()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setLiveDataObservers() {
        homeViewModel.removeWishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    homeViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    homeViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<RemoveWishListResponse> -> {
                    homeViewModel.showProgressBar(false)

                    state.response?.message?.let { homeViewModel.showSnackbarMessage("Item sucessfully deleted from wishlist") }

                }
            }
        }

        homeViewModel.addWishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    homeViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    homeViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)

                }
                is ResponseHandler.OnSuccessResponse<AddWishListResponse> -> {
                    homeViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        state.response.message?.let { homeViewModel.showSnackbarMessage("Item added to wishlist Sucessfully") }
                    }
                }
            }

        }

        homeViewModel.homeResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    homeViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    homeViewModel.showProgressBar(false)
                    MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)
                    state.message.let { homeViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<HomeResponse> -> {
                    homeViewModel.showProgressBar(false)

                    categoryItemClick()
                    subCategory()

                    parentSubCategoryAdapter.notifyDataSetChanged()
                    adapter.notifyDataSetChanged()

                    //list.clear()
                    list.addAll(state.response?.featuredCategories as ArrayList<HomeResponse.FeaturedCategory>)
                    adapter.updateCategoryItemData(list)
/*                    parentDataListFeature.clear()
                    parentDataListFeature.addAll(state.response?.featuredCategories as ArrayList<HomeResponse.FeaturedCategory>)
                    adapter.updateCategoryItemData(parentDataListFeature)*/



                    parentDataList.clear()
                    parentDataList.addAll(state.response.carousel.filter { list ->
                        list.id != "featuredProduct" &&
                                list.id != "newProduct" &&
                                list.id != "hotDeals"
                    } as ArrayList<HomeResponse.Carousel>)
                    parentSubCategoryAdapter.updateCategoryItemData(parentDataList)


                    bannerList.clear()
                    Log.d("Parent Data", " bannerList == ${bannerList.size}")

                    for (i in parentDataList) {
                        if (i.type == "image") {
                            i.banners?.let {
                                bannerList.addAll(i.banners as ArrayList<HomeResponse.Carousel.Banner>)
                            }
                        }
                    }
                    Log.d("Parent Data", " bannerList == ${bannerList.size}")
                    viewPagerSlider()

                    childDataList.clear()
                    childDataList.addAll(state.response.carousel?.get(0)?.productList as ArrayList<HomeResponse.Carousel.Product>)





                }
            }
        }

        homeViewModel.addToCartResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    homeViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    homeViewModel.showProgressBar(false)

                    MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)


                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<AddToCartResponse> -> {
                    homeViewModel.showProgressBar(false)


                    if (state.response?.success == true) {

                        state.response.message?.let { homeViewModel.showSnackbarMessage(it) }
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

    override fun getViewModel(): HomeViewModel {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return homeViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun subCategory() {

        parentSubCategoryAdapter = ParentSubCategoryAdapter(requireContext(), object :
            HomeFragmentItemClickInterface {
            override fun onItemClick(position: Int, view: View, data: HomeResponse.Carousel) {
                when (data.type) {
                    "product" -> {
                        if (data.label.toString().contains("WINTER SALE❄️")) {
                            subCategory("181", data.label.toString())
                            Log.d("TAG", "@@@sk  :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Best Seller")) {
                            subCategory("3", "customCarousel")
                            Log.d("TAG", "@@@sk  :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Baby Diapers")) {
                            subCategory("229", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Paper Products Collection")) {
                            subCategory("24", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Cooking Oil")) {
                            subCategory("231", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Beverages Collections")) {
                            subCategory("4", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Gifts & Household")) {
                            subCategory("10", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                    }
                }
            }

            @SuppressLint("CommitPrefEdits")
            override fun onSubItemClick(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product
            ) {

                val action =
                    HomeFragmentDirections.actionMnHomeToProductDatailsFragment(data.entityId.toString())
                findNavController().navigate(action)

            }

            @SuppressLint("CommitPrefEdits")
            override fun onAddToCartClick(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product
            ) {

                homeViewModel.getAddToCart(
                    customerToken.toString(),
                    data.entityId.toString(),
                    quoteId.toString(),
                )
                //Toast.makeText(requireContext(), data.name.toString(), Toast.LENGTH_SHORT).show()


            }

            override fun onClickFeaturedCategory(
                position: Int,
                view: View,
                data: HomeResponse.FeaturedCategory
            ) {
            }

            override fun onRemoveCard(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product,
                count: Int
            ) {
            }

            override fun onAddCard(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product,
                count: Int
            ) {
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onAddWishList(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product
            ) {
                homeViewModel.addWishList(
                    customerToken.toString(),
                    data.entityId.toString()
                )
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onRemoveWishList(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product
            ) {
                homeViewModel.removeWishList(
                    customerToken = customerToken.toString(),
                    itemId = data.wishlistItemId.toString()
                )
            }

        }, parentDataList, childDataList)
        getDataBinding().rvSubCategory.adapter = parentSubCategoryAdapter
    }

    private fun categoryItemClick() {
        adapter = CategoryAdapter(
            requireContext(),
            object : HomeFragmentItemClickInterface {

                override fun onItemClick(position: Int, view: View, data: HomeResponse.Carousel) {

                }

                override fun onSubItemClick(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product
                ) {


                }

                override fun onAddToCartClick(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product
                ) {

                }

                override fun onClickFeaturedCategory(
                    position: Int,
                    view: View,
                    data: HomeResponse.FeaturedCategory
                ) {
                    val action =
                        HomeFragmentDirections.actionMnHomeToCategoryItemFragment(id = data.id)
                    findNavController().navigate(action)
                }

                override fun onRemoveCard(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product,
                    count: Int
                ) {

                }

                override fun onAddCard(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product,
                    count: Int
                ) {

                }

                override fun onAddWishList(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product
                ) {

                }

                override fun onRemoveWishList(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product
                ) {

                }
            },
            list
        )

        getDataBinding().rvCategory.adapter = adapter
    }

    private fun subCategory(id: String, label: String) {
        val action = HomeFragmentDirections.actionMnHomeToCategoryItemFragment(id, label)
        findNavController().navigate(action)
    }

    private fun viewPagerSlider() {
        viewPagerAdapter = ViewPagerAdapter(requireActivity(), bannerList)
        getDataBinding().vpSlider.adapter = viewPagerAdapter

        val DELAY_MS: Long = 3000
        val PERIOD_MS: Long = 3000

        val handler = Handler(Looper.getMainLooper())

        val update = Runnable {
            if (getDataBinding().vpSlider.currentItem === viewPagerAdapter.itemCount - 1) {
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

        getDataBinding().tab.removeAllTabs();
        TabLayoutMediator(
            getDataBinding().tab,
            getDataBinding().vpSlider
        ) { tab, position -> }.attach()
    }


}


//region start homeFragment
/*
class HomeFragment : Fragment(){

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding
    private lateinit var addToCartViewModel: AddToCartViewModel
    lateinit var sh: SharedPreferences

    private lateinit var adapter: CategoryAdapter
    private var list = ArrayList<HomeResponse.FeaturedCategory>()
    private var bannerList = ArrayList<HomeResponse.Carousel.Banner>()

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var parentSubCategoryAdapter: ParentSubCategoryAdapter

    private var parentDataList = ArrayList<HomeResponse.Carousel>()
    private var childDataList = ArrayList<HomeResponse.Carousel.Product?>()


    private var timer: Timer? = null


    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        sh = requireActivity().getSharedPreferences("SignUp_Data", AppCompatActivity.MODE_PRIVATE)
        val customerToken = sh.getString("token", "")
        val quoteId = sh.getString("quoteId", "")



        val homeRepository = HomeRepository()
        val addToCartRepository = AddToCartRepository()

        homeViewModel = ViewModelProvider(
            this, HomeViewModelFactory(homeRepository)
        )[HomeViewModel::class.java]

        addToCartViewModel = ViewModelProvider(
            this, AddToCartFactory(addToCartRepository)
        )[AddToCartViewModel::class.java]


        homeViewModel.getHomeData( customerToken = customerToken.toString())
        Log.d("customerToken", "onCreate: $customerToken")

        homeViewModel.homeResponseLiveData.observe(this) {

            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {
                    binding.pbProgressBar.visibility = View.GONE

                    categoryItemClick()
                    subCategory()

                    list.clear()
                    list.addAll(it.data?.featuredCategories as ArrayList<HomeResponse.FeaturedCategory>)
                    adapter.updateCategoryItemData(list)


                    parentDataList.clear()
                    parentDataList.addAll(it.data?.carousel?.filter { list ->
                        list?.id != "featuredProduct" &&
                                list?.id != "newProduct" &&
                                list?.id != "hotDeals"
                    } as ArrayList<HomeResponse.Carousel>)
                    parentSubCategoryAdapter.updateCategoryItemData(parentDataList)


                    bannerList.clear()
                    Log.d("Parent Data", " bannerList == ${bannerList.size}")

                    for (i in parentDataList) {
                        if (i.type == "image") {
                            i.banners?.let {
                                bannerList.addAll(i.banners as ArrayList<HomeResponse.Carousel.Banner>)
                            }
                        }
                    }
                    Log.d("Parent Data", " bannerList == ${bannerList.size}")
                    viewPagerSlider()

                    childDataList.clear()
                    childDataList.addAll(it.data.carousel?.get(0)?.productList as ArrayList<HomeResponse.Carousel.Product>)
                    Log.e("Child Data", "onCreateView: $it")




                }
                is BaseResponse.Error -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                else -> {
                    binding.pbProgressBar.visibility = View.GONE
                }
            }
        }

        addToCartViewModel.addToCartResponseLiveData.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {
                    binding.pbProgressBar.visibility = View.GONE

                    val myEdit = sh.edit()

                    myEdit?.putString("productId", it.data?.itemId)
                    myEdit?.putString("quoteId", it.data?.quoteId)

                    Log.d("token", "token:${it.data?.itemId}")
                    Log.d("productId", "productId:${it.data?.itemId}")
                    Log.d("quoteId", "quoteId:${it.data?.quoteId}")

                    myEdit?.putBoolean("isCheckData", true)?.apply()
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = homeViewModel

        sh = requireActivity().getSharedPreferences("SignUp_Data", AppCompatActivity.MODE_PRIVATE)
        subCategory()
        categoryItemClick()
        viewPagerSlider()
*/
/*
        binding.tbToolbar.etSearch.setOnClickListener(this)*//*


        binding.tbToolbar.etSearch.setOnClickListener {
            val action=HomeFragmentDirections.actionMnHomeToSearchFragment()
            findNavController().navigate(action)
        }


        return binding.root
    }



    private fun subCategory() {

        parentSubCategoryAdapter = ParentSubCategoryAdapter(requireContext(), object :
            HomeFragmentItemClickInterface {
            override fun onItemClick(position: Int, view: View, data: HomeResponse.Carousel) {
                when (data.type) {
                    "product" -> {
                        if (data.label.toString().contains("WINTER SALE❄️")) {
                            subCategory("181", data.label.toString())
                            Log.d("TAG", "@@@sk  :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Best Seller")) {
                            subCategory("3", "customCarousel")
                            Log.d("TAG", "@@@sk  :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Baby Diapers")) {
                            subCategory("229", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Paper Products Collection")) {
                            subCategory("24", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Cooking Oil")) {
                            subCategory("231", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Beverages Collections")) {
                            subCategory("4", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                        if (data.label.toString().contains("Gifts & Household")) {
                            subCategory("10", data.label.toString())
                            Log.d("TAG", "@@@sk :: onCreateView: ${data.id}  ${data.type}")
                        }
                    }
                }
            }

            @SuppressLint("CommitPrefEdits")
            override fun onSubItemClick(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product
            ) {

                val action=HomeFragmentDirections.actionMnHomeToProductDatailsFragment(data.entityId.toString())
                findNavController().navigate(action)

            }

            @SuppressLint("CommitPrefEdits")
            override fun onAddToCartClick(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product
            ) {
                addToCartViewModel.getAddToCart(
                    customerToken = sh.getString("token", "").toString(),
                    productId = data.entityId.toString(),
                    quoteId = sh.getString("quoteId","").toString()
                )
                Toast.makeText(requireContext(), data.name.toString(), Toast.LENGTH_SHORT).show()



            }

            override fun onClickFeaturedCategory(
                position: Int,
                view: View,
                data: HomeResponse.FeaturedCategory
            ) {


            }

            override fun onRemoveCard(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product,
                count: Int
            ) {


                val quoteId = sh.getString("quoteId", "")

                addToCartViewModel.removeCart(
                    customerToken = sh.getString("token", "").toString(),
                    quoteId=quoteId.toString(),
                    itemId = data.itemId.toString()
                )
                Toast.makeText(requireContext(), data.name.toString(), Toast.LENGTH_SHORT).show()

            }

            override fun onAddCard(
                position: Int,
                view: View,
                data: HomeResponse.Carousel.Product,
                count: Int
            ) {

            }

        }, parentDataList, childDataList)
        binding.rvSubCategory.adapter = parentSubCategoryAdapter
    }

    private fun categoryItemClick() {
        adapter = CategoryAdapter(
            requireContext(),
            object : HomeFragmentItemClickInterface {

                override fun onItemClick(position: Int, view: View, data: HomeResponse.Carousel) {

                }

                override fun onSubItemClick(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product
                ) {


                }

                override fun onAddToCartClick(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product
                ) {

                }

                override fun onClickFeaturedCategory(
                    position: Int,
                    view: View,
                    data: HomeResponse.FeaturedCategory
                ) {
                    val action =
                        HomeFragmentDirections.actionMnHomeToCategoryItemFragment(id = data.id)
                    findNavController().navigate(action)
                }

                override fun onRemoveCard(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product,
                    count: Int
                ) {

                }

                override fun onAddCard(
                    position: Int,
                    view: View,
                    data: HomeResponse.Carousel.Product,
                    count: Int
                ) {

                }
            },
            list
        )

        binding.rvCategory.adapter = adapter
    }

    private fun subCategory(id: String, label: String) {
        val action = HomeFragmentDirections.actionMnHomeToCategoryItemFragment(id, label)
        findNavController().navigate(action)
    }

    private fun viewPagerSlider() {
        viewPagerAdapter = ViewPagerAdapter(requireActivity(), bannerList)
        binding.vpSlider.adapter = viewPagerAdapter

        val DELAY_MS: Long = 3000
        val PERIOD_MS: Long = 3000

        val handler = Handler(Looper.getMainLooper())

        val update = Runnable {
            if (binding.vpSlider.currentItem === viewPagerAdapter.itemCount - 1) {
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
*/
/*
    override fun onClick(v: View?) {
        when(v?.id) {
            binding.tbToolbar.etSearch.id -> {
                val action=HomeFragmentDirections.actionMnHomeToSearchFragment()
                findNavController().navigate(action)
            }
        }
    }*//*

}*/
//endregion