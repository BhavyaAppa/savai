package com.example.alokozapshopapplication.ui.fragment.aboutfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentAboutBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.viewmodel.NewsMediaViewModel


class AboutFragment : FragmentBase<ViewModelBase, FragmentAboutBinding>() {

    private lateinit var viewModelBase: ViewModelBase

    override fun getLayoutId(): Int {
        return R.layout.fragment_about
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
        getDataBinding().cdCardViewTermsPrivacy.setOnClickListener {
            val action =AboutFragmentDirections.actionAboutFragmentToTermsPrivacyFragment()
            findNavController().navigate(action)

        }

        getDataBinding().cdCardViewPrivacyState.setOnClickListener {

            findNavController().navigate(R.id.privacyStatementFragment)

        }
        getDataBinding().cdCardViewRefundEx.setOnClickListener {
            val action =AboutFragmentDirections.actionAboutFragmentToRefundExchangeFragment()
            findNavController().navigate(action)

        }
        getDataBinding().cdCardViewShippingDelivery.setOnClickListener {
            val action =AboutFragmentDirections.actionAboutFragmentToShippingDeliveryFragment()
            findNavController().navigate(action)

        }
        getDataBinding().cdCardViewNewsMedia.setOnClickListener {
            val action =AboutFragmentDirections.actionAboutFragmentToNewsMediaFragment()
            findNavController().navigate(action)

        }

    }

    override fun getViewModel(): ViewModelBase {
        viewModelBase = ViewModelProvider(this)[ViewModelBase::class.java]
        return viewModelBase
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
      showSnackbar(message.toString())
    }

}