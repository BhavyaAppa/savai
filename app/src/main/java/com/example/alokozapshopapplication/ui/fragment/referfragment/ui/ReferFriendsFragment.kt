package com.example.alokozapshopapplication.ui.fragment.referfragment.ui

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentReferFriendsBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.referfragment.model.ReferResponse
import com.example.alokozapshopapplication.ui.fragment.referfragment.viewmodel.RefarViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler

import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey


class ReferFriendsFragment : FragmentBase<RefarViewModel, FragmentReferFriendsBinding>() {

    private lateinit var refarViewModel: RefarViewModel
    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")
    val quoteId = MyPreference.getValueString(PrefKey.QUOTE_ID, "")


    override fun getLayoutId(): Int {
        return R.layout.fragment_refer_friends
    }

    override fun setupToolbar() {
        (activity as MainActivity).setToolbarTitle(
            imageCenter = false,
            imageArrowSearch = false,
            imageArrow = true,
            imageSearch = false,
            false


        )
    }

    override fun initializeScreenVariables() {

        getDataBinding().lifecycleOwner = viewLifecycleOwner

        getDataBinding().tbToolbar.ivArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        if (getDataBinding().tbToolbar.tvReferAndEarn.visibility == View.GONE) {
            getDataBinding().tbToolbar.tvReferAndEarn.visibility = View.VISIBLE
        } else {
            getDataBinding().tbToolbar.tvReferAndEarn.visibility = View.GONE
        }

        getDataBinding().cdViewRewardsHistory.setOnClickListener {
            val action =
                ReferFriendsFragmentDirections.actionReferFriendsFragmentToRewardHistoryFragment()
            findNavController().navigate(action)
        }

        setLiveDataObservers()
        refarViewModel.referFriend(
            customerToken = customerToken.toString(),
            quoteId = quoteId.toString()
        )
    }

    private fun setLiveDataObservers() {
        refarViewModel.referResponseLiveData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    refarViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    refarViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<ReferResponse> -> {
                    refarViewModel.showProgressBar(false)
                    getDataBinding().referModel = state.response
                }
            }
        }
    }


    override fun getViewModel(): RefarViewModel {
        refarViewModel = ViewModelProvider(this)[RefarViewModel::class.java]
        return refarViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

    }

}
