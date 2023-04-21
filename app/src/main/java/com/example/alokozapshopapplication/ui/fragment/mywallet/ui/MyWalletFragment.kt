package com.example.alokozapshopapplication.ui.fragment.mywallet.ui


import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentMyWalletBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase


class MyWalletFragment : FragmentBase<ViewModelBase, FragmentMyWalletBinding>() {



    private lateinit var viewModelBase: ViewModelBase

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_wallet

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

        if (getDataBinding().tbToolbar.tvWalletHistory.visibility == View.GONE &&
            getDataBinding().tbToolbar.tvWalletAmount.visibility == View.GONE
        ) {
            getDataBinding().tbToolbar.tvWalletHistory.visibility = View.VISIBLE
            getDataBinding().tbToolbar.tvWalletAmount.visibility = View.VISIBLE
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