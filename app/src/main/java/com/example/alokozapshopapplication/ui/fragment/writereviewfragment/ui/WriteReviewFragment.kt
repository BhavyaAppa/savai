package com.example.alokozapshopapplication.ui.fragment.writereviewfragment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentSearchBinding
import com.example.alokozapshopapplication.databinding.FragmentWriteReviewBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.searchfragment.viewmodel.SaerchViewModel


class WriteReviewFragment : FragmentBase<ViewModelBase, FragmentWriteReviewBinding>() {

    private lateinit var viewModelBase: ViewModelBase

    override fun getLayoutId(): Int {
        return R.layout.fragment_write_review

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

    }

    override fun getViewModel(): ViewModelBase {
        viewModelBase = ViewModelProvider(this)[ViewModelBase::class.java]
        return viewModelBase
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
       showSnackbar(message.toString())
    }

}