package com.example.alokozapshopapplication.ui.fragment.address.ui


import androidx.lifecycle.ViewModelProvider
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentAddressBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.base.ViewModelBase

class AddressFragment : FragmentBase<ViewModelBase, FragmentAddressBinding>() {



    private lateinit var viewModelBase: ViewModelBase

    override fun getLayoutId(): Int {
        return R.layout.fragment_address

    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataBinding().tvHome.isSelected=true
        getDataBinding().tvOffice.isSelected = false
        getDataBinding().tvOther.isSelected = false

    }*/
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
        getDataBinding().tvHome.isSelected = true
        getDataBinding().tvHome.setOnClickListener {
            /*getDataBinding().tvHome.setBackgroundResource(R.drawable.demo)
            getDataBinding().tvHome.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_main_home, 0, 0, 0);
            getDataBinding().tvHome.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_dark_main));*/
            it.isSelected = true
            getDataBinding().tvOffice.isSelected = false
            getDataBinding().tvOther.isSelected = false
        }


        getDataBinding().tvOffice.setOnClickListener {
            /*getDataBinding().tvOffice.setBackgroundResource(R.drawable.demo)
            getDataBinding().tvOffice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_office_purple, 0, 0, 0);
            getDataBinding().tvOffice.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_dark_main));*/
            it.isSelected = true
            getDataBinding().tvHome.isSelected = false
            getDataBinding().tvOther.isSelected = false
        }


        getDataBinding().tvOther.setOnClickListener {
               /* getDataBinding().tvOther.setBackgroundResource(R.drawable.demo)
                getDataBinding().tvOther.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_town_purple, 0, 0, 0);
                getDataBinding().tvOther.setTextColor(ContextCompat.getColor(requireContext(),R.color.purple_dark_main))
         */
            it.isSelected = true
            getDataBinding().tvHome.isSelected = false
            getDataBinding().tvOffice.isSelected = false
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