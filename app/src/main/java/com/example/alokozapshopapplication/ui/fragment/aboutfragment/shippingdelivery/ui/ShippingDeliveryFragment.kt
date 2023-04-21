package com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentShippingDeliveryBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.model.ShippingDeliveryResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.viewmodel.ShippingDeliveryViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler


class ShippingDeliveryFragment : FragmentBase<ShippingDeliveryViewModel, FragmentShippingDeliveryBinding>() {

    private lateinit var shippingDeliveryViewModel: ShippingDeliveryViewModel
    override fun getLayoutId(): Int {

        return R.layout.fragment_shipping_delivery
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
        getDataBinding().deliveryShippingViewModel = shippingDeliveryViewModel

        setLiveDataObservers()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setLiveDataObservers() {
        shippingDeliveryViewModel.getShippingDelivery()


        shippingDeliveryViewModel.shippingDeliveryResponce.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    shippingDeliveryViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    shippingDeliveryViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<ShippingDeliveryResponce> -> {
                    shippingDeliveryViewModel.showProgressBar(false)
                    state.toString()
                    Log.e("TAG", "onCreateView: ${state.response}")
                    getDataBinding().wbShippingDelivery.apply {
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

    override fun getViewModel(): ShippingDeliveryViewModel? {
        shippingDeliveryViewModel = ViewModelProvider(this)[ShippingDeliveryViewModel::class.java]
        return shippingDeliveryViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

    }


}





