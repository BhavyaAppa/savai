package com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.ui


import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentRewardHistoryBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.base.FragmentBase
import com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.model.RewardHistoryResponse
import com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.viewmodel.RewardHistoryViewModel
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey


class RewardHistoryFragment : FragmentBase<RewardHistoryViewModel, FragmentRewardHistoryBinding>(){

    private lateinit var rewardHistoryViewModel: RewardHistoryViewModel
    val customerToken = MyPreference.getValueString(PrefKey.ACCESS_TOKEN, "")

    override fun getLayoutId(): Int {
        return R.layout.fragment_reward_history
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


        if (getDataBinding().tbToolbar.tvRewardsHistory.visibility == View.GONE &&
            getDataBinding().tbToolbar.tvTotalRewards.visibility == View.GONE
        ) {
            getDataBinding().tbToolbar.tvRewardsHistory.visibility = View.VISIBLE
            getDataBinding().tbToolbar.tvTotalRewards.visibility = View.VISIBLE
        } else {
            getDataBinding().tbToolbar.tvRewardsHistory.visibility = View.GONE
            getDataBinding().tbToolbar.tvTotalRewards.visibility = View.GONE
        }


        setLiveDataObservers()
    }

    override fun getViewModel(): RewardHistoryViewModel {
        rewardHistoryViewModel = ViewModelProvider(this)[RewardHistoryViewModel::class.java]
        return rewardHistoryViewModel
    }

    override fun unAuthorizationUser(message: String?, messageCode: String?) {

    }

    fun setLiveDataObservers() {
        rewardHistoryViewModel.rewardHistory(customerToken = customerToken.toString())


        rewardHistoryViewModel.rewardHistoryResponceData.observe(viewLifecycleOwner) { state ->
            if (state == null) {
                return@observe
            }
            when (state) {
                is ResponseHandler.Loading -> {
                    rewardHistoryViewModel.showProgressBar(true)
                }
                is ResponseHandler.OnFailed -> {
                    rewardHistoryViewModel.showProgressBar(false)
                    httpFailedHandler(state.code, state.message, state.messageCode)
                }
                is ResponseHandler.OnSuccessResponse<RewardHistoryResponse> -> {
                    rewardHistoryViewModel.showProgressBar(false)
                    if (state.response?.success != null){
                        getDataBinding().rewardHistoryModel=state.response
                        getDataBinding().tbToolbar.rewardHistoryModel=state.response
                        getDataBinding().clEmptyCart.visibility =View.VISIBLE
                    }
                }
            }
        }
    }

}



