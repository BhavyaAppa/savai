package com.example.alokozapshopapplication.ui.fragment.forgotpassword.ui


import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentForgotPasswordBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.forgotpassword.forgotviewmodel.ForgotViewModel



class ForgotPasswordFragment : FragmentBase<ForgotViewModel, FragmentForgotPasswordBinding>() {


    private lateinit var forgotViewModel: ForgotViewModel


    override fun getLayoutId(): Int {
        return R.layout.fragment_forgot_password
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

        getDataBinding().forgotViewModel = forgotViewModel
        forgotViewModel.forgotClick.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), " Send Successfully", Toast.LENGTH_SHORT).show()
            val action =ForgotPasswordFragmentDirections.actionForgotPasswordFragmentToOTPVerificationFragment()
            findNavController().navigate(action)
        }

        textWatcherForgotPassword()
    }

    override fun getViewModel(): ForgotViewModel? {
        forgotViewModel = ViewModelProvider(this)[ForgotViewModel::class.java]
        return forgotViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    private fun textWatcherForgotPassword(){
        getDataBinding().etEmailForPassword.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().trim().isEmpty()){
                    getDataBinding().etEmailForPassword.isEnabled=true
                    getDataBinding().etMobileForForgot.isEnabled=true

                }else{
                    getDataBinding().etMobileForForgot.isEnabled=false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty() ){
                    getDataBinding().etEmailForPassword.isEnabled=true
                    getDataBinding().etMobileForForgot.isEnabled=true
                }
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty() ){
                    getDataBinding().etEmailForPassword.isEnabled=true
                }else{
                    getDataBinding().etMobileForForgot.isEnabled=false
                }
            }
        })

        getDataBinding().etMobileForForgot.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().trim().isEmpty()){
                    getDataBinding().etEmailForPassword.isEnabled=true
                    getDataBinding().etMobileForForgot.isEnabled=true
                }else{
                    getDataBinding().etEmailForPassword.isEnabled=false
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()){
                    getDataBinding().etEmailForPassword.isEnabled=true
                    getDataBinding().etMobileForForgot.isEnabled=true
                }
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty() ){
                    getDataBinding().etMobileForForgot.isEnabled=true
                }else{
                    getDataBinding().etEmailForPassword.isEnabled=false
                }
            }
        })
    }
}