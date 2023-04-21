package com.example.alokozapshopapplication.ui.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.ui.utils.DebugLog
import com.example.alokozapshopapplication.ui.utils.LocaleManager

/**
 * Created by rajanbhavsar on 20/11/19, 1:54 PM
 * Package Name : com.appname.structure.base
 * Project Name : BrainvireStructure
 */
abstract class ActivityBase<V : ViewModelBase> : AppCompatActivity() {
    val viewModel by viewModels<ViewModelBase>()
    lateinit var navHostFragment: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    interface NoInternetRetryClick {
        fun onRetryClick()
    }




    /**
     * This method is used for Navigating from One Screen to Next Screen using Navigation
     * Direction graph.
     * @param navigationId This is the Id of the Navigation Graph Action
     */
    fun navigateToNextScreen(navigationId: Int) {
        try {
            navHostFragment.findNavController().navigate(navigationId)
        } catch (e: Exception) {
            DebugLog.print(e)
        }
    }

    /**
     * This is the Method to initialize the variable at base level for Navigating from Single Class.
     * @param navHostFragment This is the Id of the NavHost Fragment or  FragmentContainerView.
     */
    fun setNavaigationHostFragment(navHostFragment: NavHostFragment) {
        this.navHostFragment = navHostFragment
    }


    fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.navHostFragment)
    }


    /**
     * This is the method used for setup the Configuration change with Language locale.
     *
     */
    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }


}