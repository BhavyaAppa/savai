package com.example.alokozapshopapplication.ui

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.alokozapshopapplication.ui.network.ApiClient
import com.example.alokozapshopapplication.ui.utils.LocaleManager
import com.example.alokozapshopapplication.ui.utils.LocaleUtils
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey


/**
 * Created by rajanbhavsar on 20/11/19, 12:44 PM
 * Package Name : com.appname
 * Project Name : BrainvireStructure
 */
class BaseStructureApplication : Application() {



    override fun onCreate() {
        super.onCreate()
        ApiClient.initRetrofit()
        MyPreference.init(this)


    }

    init {
        instance=this

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)


    }

    companion object {
        private var instance: BaseStructureApplication? = null
        fun applicationContext(): BaseStructureApplication {
            return instance as BaseStructureApplication
        }
    }
}