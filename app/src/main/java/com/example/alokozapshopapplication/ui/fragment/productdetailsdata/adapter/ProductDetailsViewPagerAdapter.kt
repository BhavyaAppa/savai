package com.example.alokozapshopapplication.ui.fragment.productdetailsdata.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.alokozapshopapplication.ui.fragment.descriptionfragment.ui.DescriptionFragment
import com.example.alokozapshopapplication.ui.fragment.ingrediants.ui.IngrediantsFragment
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse
import com.example.alokozapshopapplication.ui.fragment.reviewsfragment.ui.ReviewsFragment




class ProductDetailsViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    val productDetailsModel: ProductDetailsResponse?
) :
    FragmentStateAdapter(fragmentActivity) {


    private  val NUM_TABS = 3

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return  when (position) {
            0 -> DescriptionFragment(productDetailsModel)
            1 ->  IngrediantsFragment(productDetailsModel)
            2 ->  ReviewsFragment(productDetailsModel)
            else -> {
                throw  Resources.NotFoundException("Position not found")
            }
        }

    }
}