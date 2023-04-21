package com.example.alokozapshopapplication.ui.fragment.signupfragment.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentSignUpBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.api.ApiInterface
import com.example.alokozapshopapplication.ui.api.ApiRetrofitObject
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.loginfragment.ui.LoginFragmentDirections
import com.example.alokozapshopapplication.ui.fragment.signupfragment.repository.SignUpRepository
import com.example.alokozapshopapplication.ui.fragment.signupfragment.signupmodel.SignUpResponce
import com.example.alokozapshopapplication.ui.fragment.signupfragment.viewmodel.SignUpViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.BaseResponse
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey
import com.example.alokozapshopapplication.ui.utils.SharedPrefrence
import com.google.android.material.snackbar.Snackbar


class SignUpFragment : FragmentBase<SignUpViewModel, FragmentSignUpBinding>(){
    private lateinit var signUpViewModel: SignUpViewModel


    override fun getLayoutId(): Int  = R.layout.fragment_sign_up

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = false,
            imageArrow = true,
            imageSearch = false,
            true
        )
    }

    private fun termsPrivacy() {
        getDataBinding().tvTermsPrivacy.movementMethod = LinkMovementMethod.getInstance()
        val terms: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToAboutFragment()
                findNavController().navigate(action)

            }
        }

        val privacy: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToAboutFragment()
                findNavController().navigate(action)

            }
        }

        val spannables =
            SpannableString(resources.getString(R.string.sign_up_terms_privacy))

        spannables.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.sky_blue)),
            39,
            57,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        spannables.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.sky_blue)),
            62,
            76,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannables.setSpan(
            terms,
            39,
            57,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannables.setSpan(
            privacy,
            62,
            76,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        getDataBinding().tvTermsPrivacy.text = spannables

    }
    private fun alreadyAccountSignIn() {
        getDataBinding().tvAlreadyAccountSignIn.movementMethod =
            LinkMovementMethod.getInstance()
        val signIn: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }

        val spannable =
            SpannableString(resources.getString(R.string.sign_up_already_Account))

        spannable.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.purple_dark_main
                )
            ),
            25,
            32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        spannable.setSpan(
            signIn,
            25,
            32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        getDataBinding().tvAlreadyAccountSignIn.text = spannable


    }


    override fun initializeScreenVariables() {
        getDataBinding().signViewModel = signUpViewModel
        termsPrivacy()
        alreadyAccountSignIn()

        setLiveDataObservers()

    }

    override fun getViewModel(): SignUpViewModel? {
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        return signUpViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }


    private fun setLiveDataObservers() {

        signUpViewModel.responseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    signUpViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    signUpViewModel.showProgressBar(false)

                    MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)

                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<SignUpResponce> -> {
                    signUpViewModel.showProgressBar(false)

                    state.response?.message?.let { signUpViewModel.showSnackbarMessage(it) }
                    MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)
                    state.response?.customerToken.let {
                        it?.let { it1 ->
                            MyPreference.setValueString(
                                PrefKey.ACCESS_TOKEN,
                                it1
                            )
                        }
                    }


                    state.response?.customerName.let {
                        it?.let { it1 ->
                            MyPreference.setValueString(
                                PrefKey.FIRST_NAME,
                                it1
                            )
                        }
                    }

                    state.response?.customerEmail.let {
                        it?.let { it1 ->
                            MyPreference.setValueString(
                                PrefKey.USER_EMAIL,
                                it1
                            )
                        }
                    }

                    val action = SignUpFragmentDirections.actionSignUpFragmentToMnHome()
                    findNavController().navigate(action)

                }
            }

        }


    }



/*
    private lateinit var binding: FragmentSignUpBinding
    lateinit var sh: SharedPreferences
    private lateinit var  userData : SignUpResponce

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signUpRepository = SignUpRepository()

        signUpViewModel = ViewModelProvider(this,SignUpViewModelFactory(signUpRepository))[SignUpViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)

        binding.signViewModel=signUpViewModel
        binding.lifecycleOwner=this

        sh= requireActivity().getSharedPreferences("SignUp_Data", AppCompatActivity.MODE_PRIVATE)

        binding.tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        signUpViewModel.signUpError.observe(viewLifecycleOwner, Observer {
            Snackbar.make(requireContext(), binding.root, it, Snackbar.LENGTH_SHORT).show()
        })


       signUpViewModel.signUpResponseLiveData.observe(viewLifecycleOwner) {

            when(it){
                is BaseResponse.Loading->{
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success->{
                    binding.pbProgressBar.visibility = View.GONE

                    val myEdit = sh.edit()
                    myEdit?.putString("username", it.data?.customerName)
                    myEdit?.putString("token",it.data?.customerToken)
                    Toast.makeText(requireContext(), it.data?.customerToken, Toast.LENGTH_SHORT).show()
                    Log.d("token", it.data?.customerToken.toString())
                    myEdit?.putString("email", it.data?.customerEmail)
                    myEdit?.putBoolean("isCheckData", true)?.apply()


                    val action = SignUpFragmentDirections.actionSignUpFragmentToMnHome()
                    findNavController().navigate(action)

                    Toast.makeText(requireContext(),"Your Account has been successfully created", Toast.LENGTH_SHORT).show()
                }
                is BaseResponse.Error->{
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                else->{
                    binding.pbProgressBar.visibility = View.GONE
                }
            }

        }
        termsPrivacy()
        alreadyAccountSignIn()

        return binding.root
    }


    private fun termsPrivacy() {
        binding.tvTermsPrivacy.movementMethod = LinkMovementMethod.getInstance()
        val terms: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToAboutFragment()
                findNavController().navigate(action)

            }
        }

        val privacy: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToAboutFragment()
                findNavController().navigate(action)

            }
        }

        val spannable =
            SpannableString(resources.getString(R.string.sign_up_terms_privacy))

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.sky_blue)),
            39,
            57,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.sky_blue)),
            62,
            76,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            terms,
            39,
            57,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            privacy,
            62,
            76,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.tvTermsPrivacy.text = spannable

    }
    private fun alreadyAccountSignIn() {
        binding.tvAlreadyAccountSignIn.movementMethod = LinkMovementMethod.getInstance()
        val signIn: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }

        val spannable =
            SpannableString(resources.getString(R.string.sign_up_already_Account))

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.purple_dark_main)),
            25,
            32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        spannable.setSpan(
            signIn,
            25,
            32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.tvAlreadyAccountSignIn.text = spannable

    }
    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }


*/

}