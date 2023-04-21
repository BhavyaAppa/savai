package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.ui

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentMyAccountBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.clickhandler.ClickListnersFragment
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.deleteaccountmodel.DeleteAccountResponce
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.profiledata.ProfileResponse
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.viewmodel.profileviewmodel.ProfileViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey

class MyAccountFragment : FragmentBase<ProfileViewModel, FragmentMyAccountBinding>() {
    private lateinit var profileViewModel: ProfileViewModel
    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")
    lateinit var sh: SharedPreferences

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_account
    }

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = true,
            imageArrowSearch = false,
            imageArrow = false,
            imageSearch = false
            ,
            true

        )
    }

    override fun initializeScreenVariables() {
        getDataBinding().lifecycleOwner=viewLifecycleOwner
        sh = requireActivity().getSharedPreferences("SignUp_Data", AppCompatActivity.MODE_PRIVATE)

        getDataBinding().onClick = ClickListnersFragment(this)

        getDataBinding().cdCardViewReferAndEarn.setOnClickListener {
            val action = MyAccountFragmentDirections.actionMnAccountToReferFriendsFragment()
            findNavController().navigate(action)
        }

        getDataBinding().cdCardViewLanguage.setOnClickListener {

            val action = MyAccountFragmentDirections.actionMnAccountToLanguageFragment(
                countryId = sh.getString("websiteId", "1"),
                websiteId = sh.getString("websiteId", "1")
            )
            findNavController().navigate(action)

        }

        alreadyLogin()
        alreadySignUp()
        setupListener()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLiveDataObservers()

    }

    override fun getViewModel(): ProfileViewModel {
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        return profileViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }

    fun setLiveDataObservers() {

        profileViewModel.profileResponceData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    profileViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    profileViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<ProfileResponse> -> {
                    profileViewModel.showProgressBar(false)
                    getDataBinding().profileDataViewModel = state.response

                }

            }
        }

        profileViewModel.deleteAccountResponceData.observe(this) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    profileViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    profileViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<DeleteAccountResponce> -> {
                    profileViewModel.showProgressBar(false)

                    MyPreference.clearAllData()
                    showSnackbar("LogOut Successfully")
                    getDataBinding().nsUserDashBoard.visibility = View.GONE
                    getDataBinding().nsUserAuthentication.visibility = View.VISIBLE


                }
            }
        }

    }

    private fun alreadyLogin() {
        val isCheckData = MyPreference.getValueBoolean(
            PrefKey.ISLOGIN,
            false
        )
        val customerToken =
            MyPreference.getValueString(
                PrefKey.ACCESS_TOKEN,
                ""
            )
        if (isCheckData) {
            val userName =MyPreference.getValueString(
                PrefKey.FIRST_NAME,
                ""
            )
            val userEmail =MyPreference.getValueString(
                PrefKey.USER_EMAIL,
                ""
            )

            if (userName != null) {
                getDataBinding().tvName.text = userName
                getDataBinding().tvEmail.text = userEmail
            }
            getDataBinding().nsUserAuthentication.visibility = View.GONE
            getDataBinding().nsUserDashBoard.visibility = View.VISIBLE

            profileViewModel.getProfileData(customerToken = customerToken.toString())

        } else {
            getDataBinding().nsUserAuthentication.visibility = View.VISIBLE
            getDataBinding().nsUserDashBoard.visibility = View.GONE
        }

    }

    private fun setupListener() {

        getDataBinding().btnLogOut.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("AlokozayShop")
            builder.setMessage("Are you sure you want to logOut ?")
            builder.setCancelable(false)
                .setPositiveButton(R.string.yes) { _, _ ->
                    profileViewModel.getDeleteAccount(customerToken = customerToken.toString())
                }
                .setNegativeButton(R.string.no
                ) { dialog, _ ->
                    dialog.dismiss()

                }

            val dialog = builder.create()
            dialog.show()

            val btnPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE)
            val btnNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            with(btnPositive) {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_dark_main))
            }
            with(btnNegative) {
                setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_dark_main))
            }


        }
    }

    private fun alreadySignUp() {

        val isCheckData = MyPreference.getValueBoolean(
            PrefKey.ISLOGIN,
            false
        )


        if (isCheckData) {
            val userName =MyPreference.getValueString(
                PrefKey.FIRST_NAME,
                ""
            )
            val userEmail =MyPreference.getValueString(
                PrefKey.USER_EMAIL,
                ""
            )
            if (userName != null) {
                getDataBinding().tvName.text = userName
                getDataBinding().tvEmail.text = userEmail
            }
            getDataBinding().nsUserAuthentication.visibility = View.GONE
            getDataBinding().nsUserDashBoard.visibility = View.VISIBLE
        } else {
            getDataBinding().nsUserAuthentication.visibility = View.VISIBLE
            getDataBinding().nsUserDashBoard.visibility = View.GONE
        }

    }

}









