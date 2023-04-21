package com.example.alokozapshopapplication.ui.activity

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.ActivityMainBinding
import com.example.alokozapshopapplication.ui.constant.Constant
import com.example.alokozapshopapplication.ui.fragment.cartfragment.ui.CartFragment
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.ui.CategoryFragment
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.ui.DealsFragment
import com.example.alokozapshopapplication.ui.fragment.homefragment.ui.HomeFragment
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.ui.MyAccountFragment
import com.example.alokozapshopapplication.ui.utils.LocaleManager
import com.example.alokozapshopapplication.ui.utils.LocaleUtils
import com.example.alokozapshopapplication.ui.utils.MyPreference
import com.example.alokozapshopapplication.ui.utils.PrefKey
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFrag: NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        MyPreference.getValueString(PrefKey.LANG,"en")?.let { LocaleUtils.updateConfig(this, it) }

        bottomNavigationView = binding.bottomNavigation

        navHostFrag =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFrag.navController

        //navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController!!)

        navController!!.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mnHome -> showBottomNav()
                R.id.mnAccount -> showBottomNav()
                R.id.mnDeals -> showBottomNav()
                R.id.mnCart -> showBottomNav()
                R.id.mnCategory -> showBottomNav()
                R.id.categoryItemFragment -> showBottomNav()
                R.id.productDatailsFragment -> showBottomNav()
                R.id.writeReviewFragment -> showBottomNav()
                R.id.myWishListFragment -> showBottomNav()
                else -> {
                    hideBottomNav()
                }
            }
        }



    }


    override fun onResume() {
        super.onResume()
        MyPreference.getValueString(PrefKey.LANG,"en")?.let { LocaleUtils.updateConfig(this, it) }
    }



    private fun hideBottomNav() {
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun showBottomNav() {
        binding.bottomNavigation.visibility = View.VISIBLE

    }

    fun displayProgress(t: Boolean) {
        Log.e("Hide Progress----", "-----Hide Progress")
        binding.loading = t
    }

    fun setToolbarTitle(
        imageCenter: Boolean,
        imageArrowSearch: Boolean,
        imageArrow: Boolean,
        imageSearch: Boolean,
        mainLayout: Boolean
    ) {
        binding.tlToolBar.imageArrow.isVisible = imageArrow
        binding.tlToolBar.imageCenter.isVisible = imageCenter
        binding.tlToolBar.imageArrowSearch.isVisible = imageArrowSearch
        binding.tlToolBar.imageSearch.isVisible = imageSearch
        binding.tlToolBar.mainLayout.isVisible = mainLayout

        binding.tlToolBar.etSearchWithSearch.setOnClickListener {
            navController?.navigate(R.id.action_searchFragment)
        }

        binding.tlToolBar.etSearch.setOnClickListener {
            navController?.navigate(R.id.action_searchFragment)
        }

        binding.tlToolBar.ivArrowWithSearch.setOnClickListener {
            navController?.popBackStack()
        }

        binding.tlToolBar.ivArrowWithAlokozay.setOnClickListener {
            navController?.popBackStack()
        }
    }


    override fun attachBaseContext(base: Context) {
        if (MyPreference.mSharedPref == null) {
            MyPreference.init(base)
        }
        var languageCode = MyPreference.getValueString(PrefKey.SELECTED_LANGUAGE, Constant.EN_CODE)
        useCustomConfig(base, languageCode)?.let { super.attachBaseContext(it) }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }

    open fun useCustomConfig(context: Context, languageCode: String?): Context? {
        languageCode?.let { Locale(it) }?.let { Locale.setDefault(it) }
        return if (Build.VERSION.SDK_INT >= 17) {
            val config = Configuration()
            config.setLocale(Locale(languageCode))
            context.createConfigurationContext(config)
        } else {
            val res: Resources = context.resources
            val config =
                Configuration(res.getConfiguration())
            config.locale = Locale(languageCode)
            res.updateConfiguration(config, res.getDisplayMetrics())
            context
        }
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }


    override fun onBackPressed() {

        when (navHostFrag.childFragmentManager.fragments[0]) {
            is DealsFragment, is CategoryFragment, is CartFragment, is MyAccountFragment -> {
                navController!!.navigate(R.id.mnHome)
                navController!!.clearBackStack(R.id.mnHome)
            }
            is HomeFragment -> {
                finish()
            }
            else -> {
                super.onBackPressed()
            }
        }
    }
}

