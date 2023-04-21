package com.example.alokozapshopapplication.ui.fragment.myreturns.ui


import androidx.lifecycle.ViewModelProvider
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentMyReturnsBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase


class MyReturnsFragment : FragmentBase<ViewModelBase, FragmentMyReturnsBinding>() {



    private lateinit var viewModelBase: ViewModelBase

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_returns

    }

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = false,
            imageArrow = true,
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