package com.example.alokozapshopapplication.ui.fragment.currencyfragment.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentCurrencyBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.`interface`.CurrencyListInterface
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.adapter.CurrencyListingAdapter
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.model.CurrencyResponse
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.viewmodel.CurrencyViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler


class CurrencyFragment : FragmentBase<CurrencyViewModel, FragmentCurrencyBinding>() {


    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var currencyListingAdapter: CurrencyListingAdapter
    private var currencyList = ArrayList<CurrencyResponse.AllowedCurrency>()
    lateinit var sh: SharedPreferences
    private var selectedItem: CurrencyResponse.AllowedCurrency? = null


    override fun getLayoutId(): Int = R.layout.fragment_currency

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = false,
            imageArrow = true,
            imageSearch = false,
            false


        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner = viewLifecycleOwner
        getDataBinding().currencyViewModel = currencyViewModel

        getDataBinding().tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }
        if (getDataBinding().tbToolbar.tvSelectCurrency.visibility == View.GONE) {
            getDataBinding().tbToolbar.tvSelectCurrency.visibility = View.VISIBLE
        } else {
            getDataBinding().tbToolbar.tvSelectCurrency.visibility = View.GONE
        }

        setLiveDataObservers()
        addToCurrencyAdapter()
        currencyViewModel.getCurrencyListing()

    }

    override fun getViewModel(): CurrencyViewModel {
        currencyViewModel = ViewModelProvider(this)[CurrencyViewModel::class.java]
        return currencyViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun setLiveDataObservers() {
        currencyViewModel.currencyRepositoryResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    currencyViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    currencyViewModel.showProgressBar(false)
                    state.message.let { currencyViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<CurrencyResponse> -> {
                    currencyViewModel.showProgressBar(false)
                    if (state.response?.allowedCurrencies != null) {
                        currencyList.clear()
                        currencyList.addAll(state.response.allowedCurrencies as ArrayList<CurrencyResponse.AllowedCurrency>)
                        addToCurrencyAdapter()
                        Log.e("Category Data", "onCreateView: $state.response")
                    } else {
                        showSnackbar("Currency Not Found")
                    }
                    addToCurrencyAdapter()

                }
            }
        }
    }

    private fun addToCurrencyAdapter() {
        if (currencyList.size > 0 && selectedItem == null) {
            currencyList[0].isSelected = true
            selectedItem = currencyList[0]
        }

        currencyListingAdapter =
            CurrencyListingAdapter(requireContext(), currencyList, object : CurrencyListInterface {
                @SuppressLint("NotifyDataSetChanged")
                override fun isItemClick(position: Int, data: CurrencyResponse.AllowedCurrency) {
                    if (selectedItem != null) {
                        selectedItem?.isSelected = false
                    }
                    selectedItem = data
                    selectedItem?.isSelected = true
                    currencyListingAdapter.notifyDataSetChanged()
                }
            })
        currencyListingAdapter.setList(currencyList)
        getDataBinding().rvAddCartItem.adapter = currencyListingAdapter
    }

}