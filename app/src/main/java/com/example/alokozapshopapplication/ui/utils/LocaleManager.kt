package com.example.alokozapshopapplication.ui.utils

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.example.alokozapshopapplication.databinding.FragmentLanguageBinding
import com.example.alokozapshopapplication.ui.constant.Constant
import java.util.*

/**
 * Created by rajanbhavsar on 21/11/19, 7:11 PM
 * Package Name : com.appname.structure.utils
 * Project Name : BrainvireStructure
 */
object LocaleManager {

    @SuppressLint("ObsoleteSdkInt")
    private fun updateResources(context_: Context, language: String): Context {

        DebugLog.print("languages:$language")
        var context = context_
        val config = context.resources.configuration
        val locale = Locale(language)
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setSystemLocale(config, locale)
        } else {
            setSystemLocaleLegacy(config, locale)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(locale)
            context = context.createConfigurationContext(config)
        } else {
            context.resources.updateConfiguration(config, context.resources.displayMetrics)
        }
        return context
    }


    fun setLocale(context: Context?): Context {
//        if (MyPreference.mSharedPref == null) {
//            MyPreference.init(context!!)
//        }
        var languageCode = MyPreference.getValueString(PrefKey.SELECTED_LANGUAGE, Constant.EN_CODE)
        if (languageCode.isNullOrEmpty()) {
            languageCode = Constant.EN_CODE
        }
        return updateResources(
            context!!, languageCode

        )
    }

    fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
        config.setLocale(locale)
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun setSystemLocale(config: Configuration, locale: Locale) {
        config.setLocale(locale)
    }

    /*fun setNewLocale(context: FragmentLanguageBinding, language: String) {

        MyPreference.setValueString(PrefKey.SELECTED_LANGUAGE, language)
        updateResources(context, language)
    }*/
}