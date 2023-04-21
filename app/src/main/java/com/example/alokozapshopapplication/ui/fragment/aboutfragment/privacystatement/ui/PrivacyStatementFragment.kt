package com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentPrivacyStatementBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.model.PrivacyStatementResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.viewmodel.PrivacyStatementViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler


class PrivacyStatementFragment :
    FragmentBase<PrivacyStatementViewModel, FragmentPrivacyStatementBinding>() {

    private lateinit var privacyStatementViewModel: PrivacyStatementViewModel
    override fun getLayoutId(): Int {
        return R.layout.fragment_privacy_statement
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
        getDataBinding().privacyStatementViewModel = privacyStatementViewModel
        setLiveDataObservers()
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun setLiveDataObservers() {
        privacyStatementViewModel.getPrivacyStatement()


        privacyStatementViewModel.privacyStatementResponceLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    privacyStatementViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    privacyStatementViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<PrivacyStatementResponce> -> {
                    privacyStatementViewModel.showProgressBar(false)
                    state.toString()
                    Log.e("TAG", "onCreateView: ${state.response}")
                    getDataBinding().wbPrivacyStatement.apply {
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


    override fun getViewModel(): PrivacyStatementViewModel {
        privacyStatementViewModel = ViewModelProvider(this)[PrivacyStatementViewModel::class.java]
        return privacyStatementViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

    }
}




