package com.ismailamassi.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.preference.PreferenceManager
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.model.recipe.StepDto
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.domain.utils.ConnectionLiveData
import com.ismailamassi.presentation.databinding.ActivityMainBinding
import com.ismailamassi.presentation.utils.AppLanguage
import com.ismailamassi.presentation.utils.AppTheme
import com.ismailamassi.presentation.utils.KeyboardUtils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mainBinding: ActivityMainBinding

    private val topLevelFragments = listOf(
        R.id.nav_homeFragment,
        R.id.nav_favouriteFragment,
        R.id.nav_searchFragment,
        R.id.nav_tipsListFragment,
        R.id.nav_moreFragment,
    )

    private var isFirstTime = true
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initCurrentTheme()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        configMainUI()

        initConnectionListener()

        observeLiveData()

        insertDataToDB()
    }

    private fun insertDataToDB() {
        mainViewModel.updateDatabase()
    }

    private fun initCurrentTheme() {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val currentThemeLabel = prefs.getString("key_app_theme", "system")
        val currentTheme = AppTheme.getThemeByLabel(currentThemeLabel ?: "system")

        changeAppTheme(currentTheme, false)
    }

    private fun initConnectionListener() {
        val connectionLiveData = ConnectionLiveData(application)
        connectionLiveData.observe(this) { isConnected ->
            this.isConnected = isConnected
            if (!isFirstTime) {
                if (isConnected) backOnlineLabel() else noConnection()
            }
            isFirstTime = false
        }
    }

    private fun configMainUI() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(this)
//        mainBinding.bottomNavigationView.setupWithNavController(navController)
        mainBinding.bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == mainBinding.bottomNavigationView.selectedItemId) {
                // TODO: 8/24/2021 Refresh
                return@setOnItemSelectedListener false
            }

            val currentIndex =
                topLevelFragments.indexOf(mainBinding.bottomNavigationView.selectedItemId)
            val clickIndex = topLevelFragments.indexOf(it.itemId)

            val navOptionAnimations = if (currentIndex < clickIndex) {
                //Slide ==>
                navOptions {
                    anim {
                        enter = R.anim.slide_in_right
                        exit = R.anim.slide_out_left
                        popEnter = R.anim.slide_in_left
                        popExit = R.anim.slide_out_right
                    }
                }
            } else {
                //Slide <==
                navOptions {
                    anim {
                        enter = R.anim.slide_in_left
                        exit = R.anim.slide_out_right
                        popEnter = R.anim.slide_in_right
                        popExit = R.anim.slide_out_left
                    }
                }
            }

            when (it.itemId) {
                R.id.nav_homeFragment -> {
                    navController.navigate(
                        R.id.nav_homeFragment,
                        null,
                        navOptionAnimations
                    )
                }
                R.id.nav_favouriteFragment -> {
                    navController.navigate(
                        R.id.nav_favouriteFragment,
                        null,
                        navOptionAnimations
                    )
                }
                R.id.nav_searchFragment -> {
                    navController.navigate(
                        R.id.nav_searchFragment,
                        null,
                        navOptionAnimations
                    )
                }
                R.id.nav_tipsListFragment -> {
                    navController.navigate(
                        R.id.nav_tipsListFragment,
                        null,
                        navOptionAnimations
                    )
                }
                R.id.nav_moreFragment -> {
                    navController.navigate(
                        R.id.nav_moreFragment,
                        null,
                        navOptionAnimations
                    )
                }
            }
            true
        }
    }

    private fun observeLiveData() {
        mainViewModel.loadingLiveData.observe(this) {
            Timber.tag(TAG).d("observeLiveData : Loading $it")
            mainBinding.flLoading.visibility = if (it) View.VISIBLE else View.GONE
        }

        mainViewModel.errorLiveData.observe(this) {

        }
    }

    private fun backOnlineLabel() {
        //Show Connection Restore View for 3 sec then hide
        lifecycleScope.launch {
            mainBinding.tvConnectionStatus.apply {
                visibility = View.VISIBLE
                text = getString(R.string.back_online)
                setBackgroundColor(resources.getColor(R.color.green))
                setTextColor(resources.getColor(R.color.white))
                delay(3000)
                visibility = View.GONE
            }
        }
    }

    private fun noConnection() {
        //Show Connection Lose for 3 sec then hide
        lifecycleScope.launch {
            mainBinding.tvConnectionStatus.apply {
                visibility = View.VISIBLE
                text = getString(R.string.no_connection)
                setBackgroundColor(resources.getColor(R.color.black))
                setTextColor(resources.getColor(R.color.white))
                delay(3000)
                visibility = View.GONE
            }
        }
    }

    fun changeAppTheme(appTheme: AppTheme, restartUI: Boolean) {
        Timber.tag(TAG).d("changeAppTheme : restartUI $restartUI")

        when (appTheme) {
            AppTheme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            AppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppTheme.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        if (restartUI) {
            Timber.tag(TAG).d("changeAppTheme : restartUI $restartUI")
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    fun changeAppLanguage(appLanguage: AppLanguage) {

    }

    private fun changeBottomNavigationVisibility(show: Boolean) {
        mainBinding.bottomNavigationView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (topLevelFragments.contains(destination.id)) {
            changeBottomNavigationVisibility(true)
        } else {
            changeBottomNavigationVisibility(false)
        }

        //Close Keyboard
        hideKeyboard()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}