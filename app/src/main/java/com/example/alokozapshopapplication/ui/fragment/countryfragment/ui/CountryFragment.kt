package com.example.alokozapshopapplication.ui.fragment.countryfragment.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentCountryBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.countryfragment.`interface`.CountryListInterface
import com.example.alokozapshopapplication.ui.fragment.countryfragment.adapter.CountryListingAdapter
import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse
import com.example.alokozapshopapplication.ui.fragment.countryfragment.viewmodel.CountryViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler

class CountryFragment : FragmentBase<CountryViewModel, FragmentCountryBinding>() {

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var countryListingAdapter: CountryListingAdapter
    private var countryList = ArrayList<CountryResponse.CountryData>()
    lateinit var sh: SharedPreferences
    private var selectedItem: CountryResponse.CountryData? = null
    private var selectedPosition: Int = 0


    override fun getLayoutId(): Int = R.layout.fragment_country

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
        getDataBinding().countryViewModel = countryViewModel

        getDataBinding().tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        if (getDataBinding().tbToolbar.tvSelectCountry.visibility == View.GONE) {
            getDataBinding().tbToolbar.tvSelectCountry.visibility = View.VISIBLE
        } else {
            getDataBinding().tbToolbar.tvSelectCountry.visibility = View.GONE
        }
        setLiveDataObservers()
        countryViewModel.getCountryListing()
        addToCountryAdapter()



        addToCountryAdapter()
        getDataBinding().btnContinue.setOnClickListener {
            val action = CountryFragmentDirections.actionCountryFragmentToLanguageFragment(
                countryId = countryList[selectedPosition].countryId,
                websiteId = countryList[selectedPosition].websiteId,
            )
            findNavController().navigate(action)
        }

    }

    override fun getViewModel(): CountryViewModel {
        countryViewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        return countryViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun setLiveDataObservers() {

        countryViewModel.countryRepositoryResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    countryViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    countryViewModel.showProgressBar(false)
                    state.message.let { countryViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<CountryResponse> -> {
                    countryViewModel.showProgressBar(false)
                    if (state.response?.countryData != null) {
                        countryList.clear()
                        countryList.addAll(state.response.countryData as java.util.ArrayList<CountryResponse.CountryData>)
                        addToCountryAdapter()
                        Log.e("Category Data", "onCreateView: $state.response")
                    } else {
                        showSnackbar("Items Not Added")
                    }
                }
            }
        }
    }

    private fun addToCountryAdapter() {
        if (countryList.size > 0 && selectedItem == null) {
            countryList[0].isSelected = true
            selectedItem = countryList[0]

        }
        countryListingAdapter =
            CountryListingAdapter(requireContext(), countryList, object : CountryListInterface {
                @SuppressLint("NotifyDataSetChanged", "CommitPrefEdits")
                override fun isItemClick(position: Int, data: CountryResponse.CountryData) {

                    selectedPosition = position

                    if (selectedItem != null) {
                        selectedItem?.isSelected = false
                    }
                    selectedItem = data
                    selectedItem?.isSelected = true
                    countryListingAdapter.notifyDataSetChanged()

                }
            })
        getDataBinding().rvAddCartItem.adapter = countryListingAdapter
    }

}