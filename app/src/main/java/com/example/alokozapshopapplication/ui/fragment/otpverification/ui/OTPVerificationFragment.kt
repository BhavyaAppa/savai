package com.example.alokozapshopapplication.ui.fragment.otpverification.ui


import androidx.lifecycle.ViewModelProvider
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentOTPVerificationBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase


class OTPVerificationFragment : FragmentBase<ViewModelBase, FragmentOTPVerificationBinding>() {
    private lateinit var viewModelBase: ViewModelBase


    override fun getLayoutId(): Int {
        return R.layout.fragment_o_t_p_verification
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
        getDataBinding().lifecycleOwner = viewLifecycleOwner

    }

    override fun getViewModel(): ViewModelBase? {
        viewModelBase = ViewModelProvider(this)[ViewModelBase::class.java]
        return viewModelBase
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

        showSnackbar(message.toString())
    }

}
