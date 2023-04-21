package com.example.alokozapshopapplication.ui.fragment.contactus.ui


import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentContactUsBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.contactus.contactus.ContactUsResponse
import com.example.alokozapshopapplication.ui.fragment.contactus.contactusviewmodel.ContactUsViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler

class ContactUsFragment : FragmentBase<ContactUsViewModel, FragmentContactUsBinding>() {

    private lateinit var contactUsViewModel: ContactUsViewModel

    override fun getLayoutId(): Int  = R.layout.fragment_contact_us

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
        getDataBinding().contactUsViewModel = contactUsViewModel

        setLiveDataObservers()


    }

    override fun getViewModel(): ContactUsViewModel {
        contactUsViewModel = ViewModelProvider(this)[ContactUsViewModel::class.java]
        return contactUsViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }
    private fun setLiveDataObservers() {

        contactUsViewModel.contactUsResponceLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    contactUsViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    contactUsViewModel.showProgressBar(false)
                    state.message.let { contactUsViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<ContactUsResponse> -> {
                    contactUsViewModel.showProgressBar(false)
                    state.response?.message.let { contactUsViewModel.showSnackbarMessage(it.toString()) }
                    val  action=ContactUsFragmentDirections.actionContactUsFragmentToMnAccount()
                    findNavController().navigate(action)
                }
            }

        }

    }

}