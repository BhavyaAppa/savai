package com.example.alokozapshopapplication.ui.fragment.categoryfragment.ui


import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentCategoryBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.adapter.CategoryAdapter
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.`interface`.CategoryItemInterFace
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.model.CategoryItemResponse
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.viewmodel.CategoryViewModel
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler


class CategoryFragment : FragmentBase<CategoryViewModel, FragmentCategoryBinding>() {
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter
    private var list = ArrayList<CategoryItemResponse.Category>()

    override fun getLayoutId(): Int = R.layout.fragment_category

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
        getDataBinding().categoryItemViewModel = categoryViewModel
        setLiveDataObservers()
        categoryItemClick()
        categoryViewModel.getCategoryItem()

    }


    private fun setLiveDataObservers() {

        categoryViewModel.categoryItemResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    categoryViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    categoryViewModel.showProgressBar(false)
                    state.message.let { categoryViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<CategoryItemResponse> -> {
                    categoryViewModel.showProgressBar(false)
                    list.clear()
                    list.addAll(state.response?.categories as ArrayList<CategoryItemResponse.Category>)
                    adapter.updateCategoryItemData(list)
                    Log.d("size", "setLiveDataObservers: ${list.size}")
                    categoryItemClick()
                }
            }
        }
    }


    override fun getViewModel(): CategoryViewModel {
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        return categoryViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun categoryItemClick() {

        getDataBinding().rvRecyclarView.layoutManager=GridLayoutManager(requireContext(),3,GridLayoutManager.VERTICAL, false)
        adapter = CategoryAdapter(requireContext(), object : CategoryItemInterFace {

            override fun onClick(position: Int, view: View, data: CategoryItemResponse.Category) {
                val action =
                    CategoryFragmentDirections.actionMnCategoryToCategoryItemFragment(id = data.id)
                findNavController().navigate(action)
            }

            override fun onClickOnItem(position: Int, view: View) {
            }

            override fun onAddToCartClick(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {

            }

            override fun onProductDetailClick(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {

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

            }


        }, list)
        getDataBinding().rvRecyclarView.adapter = adapter
    }

}


/*
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var adapter: CategoryAdapter
    private var list = ArrayList<CategoryItemResponse.Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoryRepository=CategoryRepository()

        categoryViewModel = ViewModelProvider(
            this, CategoryViewModelFactory(categoryRepository)
        )[CategoryViewModel::class.java]

        categoryViewModel.getCategoryItem()



    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.categoryItemViewModel=categoryViewModel
        binding.lifecycleOwner = viewLifecycleOwner


        categoryViewModel.categoryItemResponseLiveData.observe(viewLifecycleOwner){

            when(it){
                is BaseResponse.Loading->{
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success->{
                    categoryItemClick()
                    binding.pbProgressBar.visibility = View.GONE
                    list.clear()
                    list.addAll(it.data?.categories as ArrayList<CategoryItemResponse.Category>)
                    adapter.updateCategoryItemData(list)
                    Log.e("Category Data", "onCreateView: $it" )
                }
                is BaseResponse.Error->{
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                else->{
                    binding.pbProgressBar.visibility = View.GONE
                }
            }
        }

        binding.tbToolbar.etSearch.setOnClickListener {
            val action= CategoryFragmentDirections.actionMnCategoryToSearchFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }


    private fun categoryItemClick() {
        adapter= CategoryAdapter(requireContext(),object : CategoryItemInterFace {

            override fun onClick(position: Int, view: View, data: CategoryItemResponse.Category) {
                val action =CategoryFragmentDirections.actionMnCategoryToCategoryItemFragment(id=data.id)
                findNavController().navigate(action)
            }

            override fun onClickOnItem(position: Int, view: View) {
            }

            override fun onAddToCartClick(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {

            }

            override fun onProductDetailClick(
                position: Int,
                view: View,
                data: CategoryItemListResponse.Product
            ) {

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