package com.example.alokozapshopapplication.ui.fragment.myaccountfragment.clickhandler

import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.ui.MyAccountFragment
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.ui.MyAccountFragmentDirections

class ClickListnersFragment(var fragment:MyAccountFragment) {



    fun signInClick(){
        fragment.apply {
            val action = MyAccountFragmentDirections.actionMnAccountToLoginFragment()
            findNavController().navigate(action)
        }
    }

    fun signUpClick(){
        fragment.apply {
            val action = MyAccountFragmentDirections.actionMnAccountToSignUpFragment()
            findNavController().navigate(action)
        }
    }


    fun aboutAlokozapClick(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToAboutFragment()
            findNavController().navigate(action)
        }
    }

    fun countryClick(){
        fragment.apply {
            val action = MyAccountFragmentDirections.actionMnAccountToCountryFragment()
            findNavController().navigate(action)
        }
    }

    fun currencyClick(){
        fragment.apply {
            val action = MyAccountFragmentDirections.actionMnAccountToCurrencyFragment()
            findNavController().navigate(action)
        }
    }


    fun contactUsClick(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToContactUsFragment()
            findNavController().navigate(action)
        }
    }

    fun helpClick(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToHelpFragment()
            findNavController().navigate(action)
        }
    }

    fun editProfileClick(){
        fragment.apply {
            val action = MyAccountFragmentDirections.actionMnAccountToEditProfileFragment()
            findNavController().navigate(action)
        }
    }

    fun changePasswordClick(){
        fragment.apply {
            val action = MyAccountFragmentDirections.actionMnAccountToChangePasswordFragment()
            findNavController().navigate(action)
        }
    }


    fun myOrderClick(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToMyOrderFragment()
            findNavController().navigate(action)
        }
    }

    fun myReturnsClick(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToMyReturnsFragment()
            findNavController().navigate(action)
        }
    }


    fun myWalletClick(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToMyWalletFragment()
            findNavController().navigate(action)
        }
    }

    fun myAddress(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToMyAddressFragment()
            findNavController().navigate(action)
        }
    }

    fun myReviewRating(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToMyReviewRatingFragment()
            findNavController().navigate(action)
        }
    }

    fun myWishList(){
        fragment.apply {
            val action=MyAccountFragmentDirections.actionMnAccountToMyWishListFragment()
            findNavController().navigate(action)
        }
    }








}