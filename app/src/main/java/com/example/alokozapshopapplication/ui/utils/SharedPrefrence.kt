package com.example.alokozapshopapplication.ui.utils

import android.content.Context
import android.content.SharedPreferences


object SharedPrefrence {


    private const val PREFS_NAME = "shared_pref"
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue) ?: defValue
    }

    fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }





}