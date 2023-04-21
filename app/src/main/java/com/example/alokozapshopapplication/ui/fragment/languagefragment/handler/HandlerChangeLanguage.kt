package com.example.alokozapshopapplication.ui.fragment.languagefragment.handler

import android.content.Intent
import com.example.alokozapshopapplication.databinding.FragmentLanguageBinding
import com.example.alokozapshopapplication.ui.activity.MainActivity
import com.example.alokozapshopapplication.ui.constant.Constant
import com.example.alokozapshopapplication.ui.utils.LocaleManager
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.Utils

class HandlerChangeLanguage(val context: FragmentLanguageBinding, val isFrom: String) {

    /*private fun showAlertForLanguageChange(language_code: String) {
            changeLanguageCode(language_code)
    }

    private fun changeLanguageCode(languageCode: String) {
        context.let {
            MyPreference.setValueString(Constant.LANGUAGE_CODE, languageCode)
            LocaleManager.setNewLocale(it, languageCode)
            val intent = Intent(context,MainActivity::class.java)
            intent.putExtra(Constant.SCREEN_TAG, isFrom)
            Utils.startNextActivity(context as MainActivity, intent, true)
        }
    }*/


}