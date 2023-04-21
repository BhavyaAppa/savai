package com.example.alokozapshopapplication.ui.fragment.descriptionfragment.ui


import androidx.lifecycle.ViewModelProvider
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentDescriptionBinding
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse

class DescriptionFragment() : FragmentBase<ViewModelBase, FragmentDescriptionBinding>() {
    private lateinit var viewModelBase: ViewModelBase
    constructor(descriptionModel: ProductDetailsResponse?) : this() {
        this.descriptionModel = descriptionModel
    }

    private var descriptionModel: ProductDetailsResponse? = null
    override fun getLayoutId(): Int {
        return R.layout.fragment_description
    }

    override fun setupToolbar() {

    }

    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner = viewLifecycleOwner
        getDataBinding().descriptionModel = descriptionModel
    }

    override fun getViewModel(): ViewModelBase? {
        viewModelBase = ViewModelProvider(this)[ViewModelBase::class.java]
        return viewModelBase
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

        showSnackbar(message.toString())
    }


}

