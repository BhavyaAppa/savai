package com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentNewsMediaBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.model.NewsMediaResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.viewmodel.NewsMediaViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class NewsMediaFragment : FragmentBase<NewsMediaViewModel, FragmentNewsMediaBinding>() {
    private lateinit var newsMediaViewModel: NewsMediaViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_news_media
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
        getDataBinding().newMediaViewModel = newsMediaViewModel

  /*
        getDataBinding().tbToolbar.etSearch.setOnClickListener {
            val action = NewsMediaFragmentDirections.actionNewsMediaFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        getDataBinding().tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }*/
        jsonArrayObject()
        setLiveDataObservers()
    }


    private fun jsonArrayObject() {

        //Create  Instance of  2 Object and JsonArray
        val tagObj = JSONObject()
        val priceObj = JSONObject()
        val mainList = JSONArray()

        //Tag Object
        try {
            val tags = JSONArray()
            tags.put("BEST SELLER")
            tags.put("Limited Edition")
            tagObj.put("tags", tags)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        //Price Object
        try {
            val price = JSONArray()
            price.put("100")
            price.put("200")
            priceObj.put("price", price)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        // Add Object In JsonArray
        mainList.put(tagObj)
        mainList.put(priceObj)

        // Log Print
        Log.d("mainList", "json: $mainList")
    }


    override fun getViewModel(): NewsMediaViewModel {
        newsMediaViewModel = ViewModelProvider(this)[NewsMediaViewModel::class.java]
        return newsMediaViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setLiveDataObservers() {
        newsMediaViewModel.getNewsMedia()


        newsMediaViewModel.newsMediaResponceData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    newsMediaViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    newsMediaViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<NewsMediaResponce> -> {
                    newsMediaViewModel.showProgressBar(false)
                    state.toString()
                    Log.e("TAG", "onCreateView: ${state.response}")
                    getDataBinding().wbNewsMedia.apply {
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





