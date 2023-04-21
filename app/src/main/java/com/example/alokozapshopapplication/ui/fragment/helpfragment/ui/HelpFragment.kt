package com.example.alokozapshopapplication.ui.fragment.helpfragment.ui

import androidx.lifecycle.ViewModelProvider
import com.example.alokozapshopapplication.R

import com.example.alokozapshopapplication.databinding.FragmentHelpBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase


class HelpFragment : FragmentBase<ViewModelBase, FragmentHelpBinding>() {

    private lateinit var viewModelBase: ViewModelBase

    override fun getLayoutId(): Int {
        return R.layout.fragment_help

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



