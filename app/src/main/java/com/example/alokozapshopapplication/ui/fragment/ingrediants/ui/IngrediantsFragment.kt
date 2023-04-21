package com.example.alokozapshopapplication.ui.fragment.ingrediants.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentDescriptionBinding
import com.example.alokozapshopapplication.databinding.FragmentIngrediantsBinding
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse


class IngrediantsFragment() : FragmentBase<ViewModelBase, FragmentIngrediantsBinding>() {
    private lateinit var viewModelBase: ViewModelBase

    constructor(ingrediantsModel: ProductDetailsResponse?) : this() {
        this.ingrediantsModel = ingrediantsModel
    }

    private var ingrediantsModel: ProductDetailsResponse? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_ingrediants
    }

    override fun setupToolbar() {

    }

    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner = viewLifecycleOwner
        getDataBinding().ingrediantsModel = ingrediantsModel
    }

    override fun getViewModel(): ViewModelBase? {
        viewModelBase = ViewModelProvider(this)[ViewModelBase::class.java]
        return viewModelBase
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

        showSnackbar(message.toString())
    }


}




