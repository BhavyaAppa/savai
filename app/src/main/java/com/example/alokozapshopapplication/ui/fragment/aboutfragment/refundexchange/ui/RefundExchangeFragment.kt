package com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentRefundExchangeBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.model.RefundExchangeResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.viewmodel.RefundExchangeViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler


class RefundExchangeFragment  :
    FragmentBase<RefundExchangeViewModel, FragmentRefundExchangeBinding>() {

    private lateinit var refundExchangeViewModel: RefundExchangeViewModel
    override fun getLayoutId(): Int {
        return R.layout.fragment_refund_exchange
    }

    override fun setupToolbar() {

        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = true,
            imageArrow = false,
            imageSearch = false
            ,
            true


        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().refundExchangeViewModel = refundExchangeViewModel
        setLiveDataObservers()
    }

    override fun getViewModel(): RefundExchangeViewModel? {
        refundExchangeViewModel = ViewModelProvider(this)[RefundExchangeViewModel::class.java]
        return refundExchangeViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

    }


    @SuppressLint("SetJavaScriptEnabled")
    fun setLiveDataObservers() {
        refundExchangeViewModel.getRefundExchange()


        refundExchangeViewModel.refundExchangeResponce.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    refundExchangeViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    refundExchangeViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<RefundExchangeResponce> -> {
                    refundExchangeViewModel.showProgressBar(false)
                    state.toString()
                    Log.e("TAG", "onCreateView: ${state.response}")
                    getDataBinding().wbRefundExchange.apply {
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

}



