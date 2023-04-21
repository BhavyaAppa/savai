package com.example.alokozapshopapplication.ui.fragment.loginfragment.ui


import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentLoginBinding
import com.example.alokozapshopapplication.databinding.FragmentSignUpBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginResponse
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginviewmodelclass.LoginViewModel
import com.example.alokozapshopapplication.ui.fragment.signupfragment.signupmodel.SignUpResponce
import com.example.alokozapshopapplication.ui.fragment.signupfragment.ui.SignUpFragmentDirections
import com.example.alokozapshopapplication.ui.fragment.signupfragment.viewmodel.SignUpViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey


class LoginFragment : FragmentBase<LoginViewModel, FragmentLoginBinding>() {

    private lateinit var loginViewModel: LoginViewModel

    override fun getLayoutId(): Int  = R.layout.fragment_login

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
        getDataBinding().loginViewModel = loginViewModel

        textWatcherLogin()
        setLiveDataObservers()
        signUpForgot()



    }

    override fun getViewModel(): LoginViewModel? {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        return loginViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {
        showSnackbar(message.toString())
    }


    private fun textWatcherLogin() {
        getDataBinding().etEmailSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etEmailSignIn.isEnabled = true
                    getDataBinding().etMobileSignIn.isEnabled = true
                    getDataBinding().etPasswordSignIn.isEnabled = true
                } else {
                    getDataBinding().etMobileSignIn.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etEmailSignIn.isEnabled = true
                    getDataBinding().etMobileSignIn.isEnabled = true
                    getDataBinding().etPasswordSignIn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etEmailSignIn.isEnabled = true
                    getDataBinding().etPasswordSignIn.isEnabled = true
                } else {
                    getDataBinding().etMobileSignIn.isEnabled = false
                }
            }
        })

        getDataBinding().etMobileSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etEmailSignIn.isEnabled = true
                    getDataBinding().etMobileSignIn.isEnabled = true
                    getDataBinding().etPasswordSignIn.isEnabled = true
                } else {
                    getDataBinding().etEmailSignIn.isEnabled = false
                    getDataBinding().etPasswordSignIn.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etEmailSignIn.isEnabled = true
                    getDataBinding().etMobileSignIn.isEnabled = true
                    getDataBinding().etPasswordSignIn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etMobileSignIn.isEnabled = true
                } else {
                    getDataBinding().etEmailSignIn.isEnabled = false
                    getDataBinding().etPasswordSignIn.isEnabled = false
                }
            }
        })

        getDataBinding().etPasswordSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etEmailSignIn.isEnabled = true
                    getDataBinding().etPasswordSignIn.isEnabled = true
                } else {
                    getDataBinding().etMobileSignIn.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etEmailSignIn.isEnabled = true
                    getDataBinding().etPasswordSignIn.isEnabled = true
                    getDataBinding().etMobileSignIn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    getDataBinding().etPasswordSignIn.isEnabled = true
                    getDataBinding().etEmailSignIn.isEnabled = true
                } else {
                    getDataBinding().etMobileSignIn.isEnabled = false
                }
            }
        })
    }

    private fun signUpForgot() {
        getDataBinding().tvSignUpForgot.movementMethod = LinkMovementMethod.getInstance()
        val signUp: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
                findNavController().navigate(action)
            }
        }

        val forgot: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
                findNavController().navigate(action)

            }
        }

        val spannable =
            SpannableString(resources.getString(R.string.sign_in_text_dont_account_forgot_password))
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.purple_dark_main)),
            22,
            32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.purple_dark_main)),
            33,
            50,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            signUp,
            22,
            32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            forgot,
            33,
            50,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        getDataBinding().tvSignUpForgot.text = spannable
    }

    private fun setLiveDataObservers() {

        loginViewModel.loginResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    loginViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    loginViewModel.showProgressBar(false)

                    MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)

                    state.message.let { loginViewModel.showSnackbarMessage(it) }
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<LoginResponse> -> {
                    loginViewModel.showProgressBar(false)


                    if (state.response?.success == true) {

                        MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)
                        state.response.message?.let { loginViewModel.showSnackbarMessage(it) }


                        state.response.customerToken.let {
                            it?.let { it1 ->
                                MyPreference.setValueString(
                                    PrefKey.ACCESS_TOKEN,
                                    it1
                                )
                            }
                        }

                        state.response.customerName.let {
                            it?.let { it1 ->
                                MyPreference.setValueString(
                                    PrefKey.FIRST_NAME,
                                    it1
                                )
                            }
                        }

                        state.response.customerEmail.let {
                            it?.let { it1 ->
                                MyPreference.setValueString(
                                    PrefKey.USER_EMAIL,
                                    it1
                                )
                            }
                        }

                        val action = LoginFragmentDirections.actionLoginFragmentToMnHome()
                        findNavController().navigate(action)

                    } else {
                        state.response?.message?.let { loginViewModel.showSnackbarMessage(it) }
                    }

                }
            }

        }

    }
}


/*
    private lateinit var binding: FragmentLoginBinding
    lateinit var sh: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginRepository = LoginRepository()
        loginViewModel = ViewModelProvider(
            this, LoginViewModelFactory(loginRepository)
        )[LoginViewModel::class.java]
    }


    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewModel = loginViewModel

        sh= requireActivity().getSharedPreferences("SignUp_Data", AppCompatActivity.MODE_PRIVATE)



        binding.tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }


        loginViewModel.loginError.observe(viewLifecycleOwner) {
            Snackbar.make(requireContext(), binding.root, it, Snackbar.LENGTH_SHORT).show()
        }

        loginViewModel.loginResponseLiveData.observe(viewLifecycleOwner) {

            when (it) {
                is BaseResponse.Loading -> {
                    binding.pbProgressBar.visibility = View.VISIBLE
                }
                is BaseResponse.Success -> {

                    binding.pbProgressBar.visibility = View.GONE

                        if (it.data?.success == true) {

                            val myEdit = sh.edit()
                            myEdit?.putString("username", it.data.customerName)
                            myEdit?.putString("email", it.data.customerEmail)
                            myEdit?.putString("token", it.data.customerToken)
                            myEdit?.putBoolean("isCheckData", true)?.apply()

                            val action = LoginFragmentDirections.actionLoginFragmentToMnHome()
                            findNavController().navigate(action)
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.login_sucessfully),
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(
                                requireContext(),
                              "Invalid login or password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                is BaseResponse.Error -> {
                    binding.pbProgressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.login_unsucessfully),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    binding.pbProgressBar.visibility = View.GONE
                }
            }

        }

        signUpForgot()
        textWatcherLogin()

        return binding.root
    }

    private fun textWatcherLogin() {
        binding.etEmailSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    binding.etEmailSignIn.isEnabled = true
                    binding.etMobileSignIn.isEnabled = true
                    binding.etPasswordSignIn.isEnabled = true
                } else {
                    binding.etMobileSignIn.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    binding.etEmailSignIn.isEnabled = true
                    binding.etMobileSignIn.isEnabled = true
                    binding.etPasswordSignIn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    binding.etEmailSignIn.isEnabled = true
                    binding.etPasswordSignIn.isEnabled = true
                } else {
                    binding.etMobileSignIn.isEnabled = false
                }
            }
        })

        binding.etMobileSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    binding.etEmailSignIn.isEnabled = true
                    binding.etMobileSignIn.isEnabled = true
                    binding.etPasswordSignIn.isEnabled = true
                } else {
                    binding.etEmailSignIn.isEnabled = false
                    binding.etPasswordSignIn.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    binding.etEmailSignIn.isEnabled = true
                    binding.etMobileSignIn.isEnabled = true
                    binding.etPasswordSignIn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    binding.etMobileSignIn.isEnabled = true
                } else {
                    binding.etEmailSignIn.isEnabled = false
                    binding.etPasswordSignIn.isEnabled = false
                }
            }
        })

        binding.etPasswordSignIn.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().trim().isEmpty()) {
                    binding.etEmailSignIn.isEnabled = true
                    binding.etPasswordSignIn.isEnabled = true
                } else {
                    binding.etMobileSignIn.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (s.toString().trim().isEmpty()) {
                    binding.etEmailSignIn.isEnabled = true
                    binding.etPasswordSignIn.isEnabled = true
                    binding.etMobileSignIn.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isEmpty()) {
                    binding.etPasswordSignIn.isEnabled = true
                    binding.etEmailSignIn.isEnabled = true
                } else {
                    binding.etMobileSignIn.isEnabled = false
                }
            }
        })
    }

    private fun signUpForgot() {
        binding.tvSignUpForgot.movementMethod = LinkMovementMethod.getInstance()
        val signUp: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
                findNavController().navigate(action)
            }
        }

        val forgot: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
            }

            override fun onClick(p0: View) {
                val action = LoginFragmentDirections.actionLoginFragmentToForgotPasswordFragment()
                findNavController().navigate(action)

            }
        }

        val spannable =
            SpannableString(resources.getString(R.string.sign_in_text_dont_account_forgot_password))
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.purple_dark_main)),
            22,
            32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.purple_dark_main)),
            33,
            50,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            signUp,
            22,
            32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        spannable.setSpan(
            forgot,
            33,
            50,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        binding.tvSignUpForgot.text = spannable
    }
*/


