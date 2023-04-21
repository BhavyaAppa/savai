package com.example.alokozapshopapplication.ui.fragment.languagefragment.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle

import android.view.View

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentLanguageBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.constant.Constant
import com.example.alokozapshopapplication.ui.fragment.languagefragment.`interface`.LanguageListInterface
import com.example.alokozapshopapplication.ui.fragment.languagefragment.adapter.LanguageListingAdapter
import com.example.alokozapshopapplication.ui.fragment.languagefragment.model.LanguageResponce
import com.example.alokozapshopapplication.ui.fragment.languagefragment.viewmodel.LanguageViewModel
import com.example.alokozapshopapplication.ui.fragment.splashfragment.SplashFragment
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.*
import java.util.*
import kotlin.collections.ArrayList


class LanguageFragment : FragmentBase<LanguageViewModel, FragmentLanguageBinding>() {


    private lateinit var languageViewModel: LanguageViewModel

    private lateinit var languageListingAdapter: LanguageListingAdapter
    private var languageList = ArrayList<LanguageResponce.LanguageData>()

    private var selectedItem:LanguageResponce.LanguageData? = null
    val args: LanguageFragmentArgs by navArgs()



    override fun getLayoutId(): Int {
        return R.layout.fragment_language

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val countryId=args.countryId.toString()
        val websiteId=args.websiteId.toString()

        languageViewModel.getLanguageListing(
            countryId = countryId,
            websiteId = websiteId
        )
        setLiveDataObservers()
    }

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
        getDataBinding().languageViewModel = languageViewModel



        getDataBinding().tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        if( getDataBinding().tbToolbar.tvSelectLanguage.visibility == View.GONE){
            getDataBinding().tbToolbar.tvSelectLanguage.visibility = View.VISIBLE
        }else{
            getDataBinding().tbToolbar.tvSelectLanguage.visibility = View.GONE
        }

        getDataBinding().btnContinue.setOnClickListener {
            /*val action= LanguageFragmentDirections.actionLanguageFragmentToSplashFragment()
            findNavController().navigate(action)*/
            LocaleUtils.changeLanguage(requireContext(),selectedItem?.languageSortCode.toString())
            MyPreference.setValueString(PrefKey.LANG,selectedItem?.languageSortCode.toString())
            MyPreference.setValueString(PrefKey.LANG_ID,selectedItem?.languageId.toString())
            MyPreference.setValueString(PrefKey.LANG_NAME,selectedItem?.languageName.toString())
            MyPreference.setValueString(PrefKey.LANG_CHANGE,selectedItem?.languageAlignment.toString())


        }


    }




    override fun getViewModel(): LanguageViewModel {
        languageViewModel = ViewModelProvider(this)[LanguageViewModel::class.java]
        return languageViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun setLiveDataObservers() {

        languageViewModel.languageRepositoryResponseLiveData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    languageViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    languageViewModel.showProgressBar(false)
                    state.message.let { languageViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<LanguageResponce> -> {
                    languageViewModel.showProgressBar(false)
                    if (state.response?.languageData != null) {

                        state.response.languageData?.forEach {
                                languageList.clear()
                                languageList.addAll(state.response.languageData as java.util.ArrayList<LanguageResponce.LanguageData>)
                                MyPreference.setValueString("countryId",state.response.websiteId.toString())
                                MyPreference.setValueString("websiteId",state.response.websiteId.toString())
                                addToLanguageAdapter()

                        }

                    } else {
                        showSnackbar("Language Not Found")
                        state.response?.message.let {
                            if (it != null) {
                                languageViewModel.showSnackbarMessage(it)
                            }
                        }

                    }
                }
            }
        }
    }

    private fun addToLanguageAdapter() {

        if (languageList.size > 0  && selectedItem == null) {
            languageList[0].isSelected = true
            selectedItem = languageList[0]
        }
        languageListingAdapter = LanguageListingAdapter(requireContext(), languageList ,object : LanguageListInterface {
            @SuppressLint("NotifyDataSetChanged")
            override fun isItemClick(position: Int, data: LanguageResponce.LanguageData) {
                if (selectedItem != null) {
                    selectedItem?.isSelected = false
                }
                selectedItem = data
                selectedItem?.isSelected = true
                languageListingAdapter.notifyDataSetChanged()

            }
        })
        languageListingAdapter.setList(languageList)
        getDataBinding().rvAddCartItem.adapter = languageListingAdapter
    }


}