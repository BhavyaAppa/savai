package com.example.alokozapshopapplication.ui.utils

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import com.example.alokozapshopapplication.ui.activity.MainActivity
import java.util.*

object LocaleUtils {

    private const val TAG = "LocaleUtils"

    private var sLocale: Locale? = null

    fun changeLanguage(context: Context, lang: String) {
        LocaleUtils.updateConfig(context, lang)

        //MyPreference.setShowSplash(context, false)

        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
        (context as MainActivity).finish()
    }

    fun updateConfig(context: Context, lang: String) {
        val lang = lang
        Log.d(TAG, "updateConfig123: $lang")
        val myLocale: Locale
        myLocale = when (lang) {
            "en_US" -> Locale.ENGLISH
            else -> Locale(lang)
        }

        setLocale(myLocale)
        val config = Configuration()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(sLocale)
        } else {
            config.locale = sLocale
        }

        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    private fun setLocale(locale: Locale) {
        sLocale = locale
        if (sLocale != null) {
            Locale.setDefault(sLocale)
        }
    }

}