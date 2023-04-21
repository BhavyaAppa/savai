package com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentCategoryItemBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.adapter.CategorySubAdapter
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.`interface`.CategoryItemInterFace
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.model.CategoryItemResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.viewmodel.CategoryItemViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey
import com.google.android.material.tabs.TabLayout


@Suppress("UNREACHABLE_CODE")
class CategoryItemFragment : FragmentBase<CategoryItemViewModel, FragmentCategoryItemBinding>() {

    private val args: CategoryItemFragmentArgs by navArgs()
    private lateinit var categoryItemViewModel: CategoryItemViewModel
    private lateinit var adapter: CategorySubAdapter
    private var list = ArrayList<CategoryItemListResponse.Product>()


    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")

    private var isLoading: Boolean = true
    private var pageNumber = 1
    private var response: CategoryItemListResponse? = null


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
        categoryItemViewModel.getCategoryItemList(
            customerToken = customerToken.toString(),
            type = args.label.toString(),
            id = args.id.toString(),
            pageNumber

        )
        setLiveDataObservers()
    }

    override fun initializeScreenVariables() {
        getDataBinding().categorySubItemViewModel = categoryItemViewModel
        getDataBinding().lifecycleOwner = viewLifecycleOwner

        categoryItemClick()
        tabTypeClick()

        getDataBinding().clFilter.setOnClickListener {
            showSnackbar("Filter Data")

        }

        getDataBinding().rvRecyclarView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastCompletelyVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!isLoading && list.size < response?.totalCount!! && lastCompletelyVisibleItemPosition > list.size - 4) {
                    isLoading = true
                    Log.i("dx", "onScrolled: $dx")
                    categoryItemViewModel.getCategoryItemList(
                        customerToken = customerToken.toString(),
                        type = args.label.toString(),
                        id = args.id.toString(),
                        ++pageNumber
                    )
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

    }

    override fun getLayoutId(): Int = R.layout.fragment_category_item

    override fun getViewModel(): CategoryItemViewModel {
        categoryItemViewModel = ViewModelProvider(this)[CategoryItemViewModel::class.java]
        return categoryItemViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun setLiveDataObservers() {

        categoryItemViewModel.categoryItemListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    categoryItemViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    categoryItemViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                    state.message.let { categoryItemViewModel.showSnackbarMessage(it) }
                }
                is ResponseHandler.OnSuccessResponse<CategoryItemListResponse> -> {
                    categoryItemViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        isLoading = false
                        response = state.response
                        list.addAll(state.response.productList as ArrayList<CategoryItemListResponse.Product>)
                        adapter.updateCategoryItemData(list)
                        Log.i("PageNumber", "setLiveDataObservers: ${state.response.totalCount}")
                        Log.i("size", "data: ${list.size}")
                    }
                }
            }
        }

        categoryItemViewModel.addToCartResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    categoryItemViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    categoryItemViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                    state.message.let { categoryItemViewModel.showSnackbarMessage(it) }
                }
                is ResponseHandler.OnSuccessResponse<AddToCartResponse> -> {
                    categoryItemViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        state.response.message?.let { categoryItemViewModel.showSnackbarMessage(it) }
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

        categoryItemViewModel.addWishListResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    categoryItemViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    categoryItemViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                    state.message.let { categoryItemViewModel.showSnackbarMessage(it) }
                }
                is ResponseHandler.OnSuccessResponse<AddWishListResponse> -> {
                    categoryItemViewModel.showProgressBar(false)
                    if (state.response?.success == true) {
                        state.response.message?.let { categoryItemViewModel.showSnackbarMessage(it) }
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


    }

    private fun categoryItemClick() {
        adapter = CategorySubAdapter(requireContext(), object : CategoryItemInterFace {

            override fun onClick(position: Int, view: View, data: CategoryItemResponse.Category) {
            }

            override fun onClickOnItem(position: Int, view: View) {
            }

            override fun onAddToCartClick(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {
                categoryItemViewModel.getAddToCart(
                    customerToken.toString(),
                    data.entityId.toString(),
                    quoteId.toString(),
                )
            }

            override fun onProductDetailClick(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {
                val action =
                    CategoryItemFragmentDirections.actionCategoryItemFragmentToProductDatailsFragment(
                        data.entityId.toString()
                    )
                findNavController().navigate(action)
            }

            override fun onRemoveCard(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product,
                count: Int
            ) {
            }

            override fun onAddCard(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product,
                count: Int
            ) {
            }

            override fun wishList(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {
                categoryItemViewModel.addWishList(
                    customerToken.toString(),
                    data.entityId.toString()
                )
            }
        }, list)
        getDataBinding().rvRecyclarView.adapter = adapter
    }

    private fun tabTypeClick() {

        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("All")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Black Tea")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Green Tea")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Loose Tea")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Tin Tea")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Herbal Tea")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Tea Chest")
        )
        getDataBinding().tlCategoryItem.addTab(
            getDataBinding().tlCategoryItem.newTab().setText("Organic Tea")
        )


        getDataBinding().tlCategoryItem.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }


}

//region start CategoryItemFragment
/*
class CategoryItemFragment : Fragment() {
    private lateinit var binding: FragmentCategoryItemBinding
    private val  args:CategoryItemFragmentArgs by navArgs()
    private lateinit var categoryItemViewModel: CategoryItemViewModel
    private lateinit var adapter: CategorySubAdapter
    lateinit var sh: SharedPreferences
    private var list = ArrayList<CategoryItemListResponse.Product>()
    private var dataList=java.util.ArrayList<CategoryItemListResponse>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val categoryItemListRepository = CategoryItemListRepository()

        categoryItemViewModel = ViewModelProvider(
            this, CategoryItemViewModelFactory(categoryItemListRepository)
        )[CategoryItemViewModel::class.java]


        categoryItemViewModel.getCategoryListItem(type = args.label.toString(), id=args.id.toString())




    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_category_item, container, false)

        Log.e("TAG," ,"onCreateView: ${args.id}", )

        binding.categorySubItemViewModel = categoryItemViewModel

        binding.lifecycleOwner = viewLifecycleOwner

        sh = requireActivity().getSharedPreferences("SignUp_Data", AppCompatActivity.MODE_PRIVATE)







        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("All"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Black Tea"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Green Tea"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Loose Tea"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Tin Tea"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Herbal Tea"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Tea Chest"))
        binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText("Organic Tea"))

        binding.tbToolbar.etSearch.setOnClickListener {
            val action= CategoryItemFragmentDirections.actionCategoryItemFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        binding.tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }


        binding.tlCategoryItem.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        categoryItemViewModel.addToCartResponseLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {
                    binding.pbProgressBar.visibility = View.GONE
                    val myEdit = sh.edit()
                    myEdit?.putString("quoteId", it.data?.quoteId)
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

        categoryItemViewModel.categoryItemListResponseLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {
                    categoryItemClick()
                    binding.pbProgressBar.visibility = View.GONE

*/
/*
                    it.data?.childCategories?.let { childCategories ->
                        for (i in childCategories.indices){
                            if (childCategories[i]?.hasChildren == true){
                                binding.tlCategoryItem.addTab(binding.tlCategoryItem.newTab().setText(
                                    (it.data.productList as ArrayList<CategoryItemListResponse.Product>)[i].name))
                            }
                        }
                    }



*//*

                    if(it.data?.success == true){

                        list.clear()
                        list.addAll(it.data.productList as ArrayList<CategoryItemListResponse.Product>)

                        Log.d("Sub Category Item Data",list.toString())
                        Log.d("Sub Category Item Data", "onCreateView: $it")
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


        return binding.root
    }


    private fun categoryItemClick() {
        adapter= CategorySubAdapter(requireContext(),object : CategoryItemInterFace {

            override fun onClick(position: Int, view: View, data: CategoryItemResponse.Category) {

            }
            override fun onClickOnItem(position: Int, view: View) {

            }

            override fun onAddToCartClick(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {
                categoryItemViewModel.getAddToCart(
                    customerToken = sh.getString("token", "").toString(),
                    productId = data.entityId.toString(),
                    quoteId = sh.getString("quoteId","").toString()
                    )


                Toast.makeText(requireContext(), data.name.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onProductDetailClick(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {
                val action =CategoryItemFragmentDirections.actionCategoryItemFragmentToProductDatailsFragment(data.entityId.toString())
                findNavController().navigate(action)

            }

            override fun onRemoveCard(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product,
                count: Int
            ) {

            }

            override fun onAddCard(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product,
                count: Int
            ) {

            }

        },list)
        binding.rvRecyclarView.adapter = adapter
    }


}*/

//endregion