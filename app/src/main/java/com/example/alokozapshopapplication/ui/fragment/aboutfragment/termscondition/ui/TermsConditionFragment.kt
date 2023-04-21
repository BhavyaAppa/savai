package com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentTermsConditionBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.model.TermsConditionResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.viewmodel.TermsConditionViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler


class TermsConditionFragment  : FragmentBase<TermsConditionViewModel, FragmentTermsConditionBinding>() {
    private lateinit var termsConditionViewModel: TermsConditionViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_terms_condition

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
        getDataBinding().termsConditionViewModel = termsConditionViewModel

        setLiveDataObservers()
    }
    @SuppressLint("SetJavaScriptEnabled")
    fun setLiveDataObservers() {
        termsConditionViewModel.getTermsCondition()


        termsConditionViewModel.termsConditionResponce.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    termsConditionViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    termsConditionViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<TermsConditionResponce> -> {
                    termsConditionViewModel.showProgressBar(false)
                    state.toString()
                    Log.e("TAG", "onCreateView: ${state.response}")
                    getDataBinding().wbTermsCondition.apply {
                        webViewClient = WebViewClient()
                        webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(
                                view: WebView,
                                request: WebResourceRequest
                            ): Boolean {
                                val intent = Intent(Intent.ACTION_VIEW, request.url)
                                view.context.startActivity(intent)
                                return true
                            }
                        }
                        settings.javaScriptEnabled = true
                        settings.setSupportZoom(true)
                        settings.builtInZoomControls = true;
                        settings.displayZoomControls = false;

                        loadData(state.response?.content.toString(), "text/html", "UTF-8");
                    }
                }
            }
        }
    }

    override fun getViewModel(): TermsConditionViewModel {
        termsConditionViewModel = ViewModelProvider(this)[TermsConditionViewModel::class.java]
        return termsConditionViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

    }

}









