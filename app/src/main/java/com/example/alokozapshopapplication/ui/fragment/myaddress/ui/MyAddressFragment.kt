package com.example.alokozapshopapplication.ui.fragment.myaddress.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentMyAccountBinding
import com.example.alokozapshopapplication.databinding.FragmentMyAddressBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.viewmodel.profileviewmodel.ProfileViewModel

class MyAddressFragment : FragmentBase<ViewModelBase, FragmentMyAddressBinding>() {



    private lateinit var viewModelBase: ViewModelBase

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_address

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

        getDataBinding().cvNyAddress.setOnClickListener {
            val action=MyAddressFragmentDirections.actionMyAddressFragmentToAddressFragment()
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