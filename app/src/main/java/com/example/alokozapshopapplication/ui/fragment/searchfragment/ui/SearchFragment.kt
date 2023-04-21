package com.example.alokozapshopapplication.ui.fragment.searchfragment.ui

import android.content.SharedPreferences

import android.text.Editable
import android.text.TextWatcher

import android.view.View

import androidx.core.view.isVisible

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentSearchBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.searchfragment.SearchListItemInterface
import com.example.alokozapshopapplication.ui.fragment.searchfragment.`interface`.SearchCategoryInterface
import com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter.CategorySearchAdapter
import com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter.ParentProductCategoryAdapter
import com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter.ParentProductListAdapter
import com.example.alokozapshopapplication.ui.fragment.searchfragment.adapter.ProductListAdapter
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance
import com.example.alokozapshopapplication.ui.fragment.searchfragment.viewmodel.SaerchViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey

class SearchFragment : FragmentBase<SaerchViewModel, FragmentSearchBinding>(){

    private lateinit var saerchViewModel: SaerchViewModel
    lateinit var sh: SharedPreferences

    private lateinit var adapter: CategorySearchAdapter
    private var list = ArrayList<HomeResponse.FeaturedCategory>()

    private lateinit var productListAdapter: ProductListAdapter
    private var productList = ArrayList<SearchDataRespoance.Indice.Item>()


    private lateinit var parentProductListAdapter: ParentProductListAdapter
    private var parentProductDataList = java.util.ArrayList<SearchDataRespoance.Indice>()
    private var childProductDataList = java.util.ArrayList<SearchDataRespoance.Indice.Item>()

    private lateinit var parentProductCategoryAdapter: ParentProductCategoryAdapter
    private var parentProductCatList = java.util.ArrayList<SearchDataRespoance.Indice>()
    private var childProductCatList = java.util.ArrayList<SearchDataRespoance.Indice.Item>()

    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")


    override fun getLayoutId(): Int = R.layout.fragment_search

    override fun setupToolbar() {

        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = false,
            imageArrow = false,
            imageSearch = false
            ,
            false


        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner = viewLifecycleOwner

        setLiveDataObservers()

        saerchViewModel.searchCategory(customerToken=customerToken.toString())

        getDataBinding().tvCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        textWatcherSearch()
        productListAdapter()
        productListItem()
        productListCatItem()





    }

    override fun getViewModel(): SaerchViewModel {
        saerchViewModel = ViewModelProvider(this)[SaerchViewModel::class.java]
        return saerchViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun setLiveDataObservers() {

        saerchViewModel.searchResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    saerchViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    saerchViewModel.showProgressBar(false)
                    state.message.let { saerchViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<HomeResponse> -> {
                    saerchViewModel.showProgressBar(false)
                    list.clear()
                    list.addAll(state.response?.featuredCategories as ArrayList<HomeResponse.FeaturedCategory>)
                    categoryItemClick()
                }
            }
        }

        saerchViewModel.searchItemResponceLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    saerchViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    saerchViewModel.showProgressBar(false)
                    state.message.let { saerchViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<SearchDataRespoance> -> {
                    saerchViewModel.showProgressBar(false)

                    //1.Product Details RecyclarView
                    productList.clear()
                    if ((state.response?.indices?.size ?: 0) > 2)
                        productList.addAll(state.response?.indices?.get(2)?.items as ArrayList<SearchDataRespoance.Indice.Item>)


                    //2.Product Popular RecyclarView
                    parentProductDataList.clear()
                    childProductDataList.clear()
                    parentProductDataList.addAll(state.response?.indices?.filter { list ->
                        list.title != "Products" &&
                                list.title != "Products in categories"
                    } as java.util.ArrayList<SearchDataRespoance.Indice>)

                    for (i in parentProductDataList.indices) {
                        childProductDataList.addAll(state.response.indices?.get(i)?.items as ArrayList<SearchDataRespoance.Indice.Item>)
                    }


                    //3.Product In Categories RecyclarView

                    parentProductCatList.clear()
                    childProductCatList.clear()
                    parentProductCatList.addAll(state.response.indices?.filter { list ->
                        list.title != "Products" &&
                                list.title != "Popular suggestions"

                    } as java.util.ArrayList<SearchDataRespoance.Indice>)

                    for (i in parentProductCatList.indices) {
                        childProductCatList.addAll(state.response.indices?.get(i)?.items as ArrayList<SearchDataRespoance.Indice.Item>)
                    }

                    getDataBinding().ivCancel.setOnClickListener {
                        getDataBinding().ivCancel.visibility=View.GONE
                        getDataBinding().rvCategory.isVisible = true
                        getDataBinding().rvPopularSuggestions.isVisible = false
                        getDataBinding().rvProduct.isVisible = false
                        getDataBinding().rvProductDetails.isVisible = false
                        getDataBinding().etSearch.setText("")
                    }


                    val first = if ((state.response.indices?.size
                            ?: 0) > 1
                    ) state.response.indices?.get(0)?.totalItems else 0
                    val sec = if ((state.response.indices?.size
                            ?: 0) > 2
                    ) state.response.indices?.get(1)?.totalItems else 0
                    val thrd = if ((state.response.indices?.size
                            ?: 0) > 3
                    ) state.response.indices?.get(2)?.totalItems else 0
                    if (first == 0 && sec == 0 && thrd == 0) {
                        getDataBinding().rvCategory.isVisible = true
                        getDataBinding().rvPopularSuggestions.isVisible = false
                        getDataBinding().rvProduct.isVisible = false
                        getDataBinding().rvProductDetails.isVisible = false
                    } else {
                        getDataBinding().rvCategory.isVisible = false
                        getDataBinding().rvPopularSuggestions.isVisible = true
                        getDataBinding().rvProduct.isVisible = true
                        getDataBinding().rvProductDetails.isVisible = true
                    }

                    productListItem()
                    productListCatItem()
                    productListAdapter()


                }
            }
        }
    }

    private fun categoryItemClick() {
        adapter = CategorySearchAdapter(
            requireContext(),
            object : SearchCategoryInterface {
                override fun onClickCategory(
                    position: Int,
                    view: View,
                    data: HomeResponse.FeaturedCategory
                ) {
                    val action =
                        SearchFragmentDirections.actionSearchFragmentToCategoryItemFragment(id = data.id)
                    findNavController().navigate(action)
                }
            },
            list
        )

        getDataBinding().rvCategory.adapter = adapter
    }

    private fun productListAdapter() {
        productListAdapter = ProductListAdapter(requireContext(), object : SearchListItemInterface {
            override fun onProductClick(
                position: Int,
                view: View,
                data: SearchDataRespoance.Indice.Item
            ) {

            }

            override fun onProductItemClick(
                position: Int,
                view: View,
                data: SearchDataRespoance.Indice.Item
            ) {

            }

        }, productList)
        getDataBinding().rvProductDetails.adapter = productListAdapter

    }

    private fun productListItem() {

        parentProductListAdapter =
            ParentProductListAdapter(requireContext(), object : SearchListItemInterface {
                override fun onProductClick(
                    position: Int,
                    view: View,
                    data: SearchDataRespoance.Indice.Item
                ) {

                }

                override fun onProductItemClick(
                    position: Int,
                    view: View,
                    data: SearchDataRespoance.Indice.Item
                ) {
                }
            }, parentProductDataList, childProductDataList)
        getDataBinding().rvPopularSuggestions.adapter = parentProductListAdapter
    }

    private fun productListCatItem() {

        parentProductCategoryAdapter =
            ParentProductCategoryAdapter(requireContext(), object : SearchListItemInterface {
                override fun onProductClick(
                    position: Int,
                    view: View,
                    data: SearchDataRespoance.Indice.Item
                ) {

                }

                override fun onProductItemClick(
                    position: Int,
                    view: View,
                    data: SearchDataRespoance.Indice.Item
                ) {

                }
            }, parentProductCatList, childProductCatList)
        getDataBinding().rvProduct.adapter = parentProductCategoryAdapter
    }

    private fun textWatcherSearch() {
        getDataBinding().etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    getDataBinding().ivCancel.visibility = View.GONE
                } else {
                    getDataBinding().ivCancel.visibility = View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty() && count >= 3) {
                    saerchViewModel.searchItem(s.toString())
                    getDataBinding().rvCategory.visibility = View.GONE
                }
            }
        })
    }

}