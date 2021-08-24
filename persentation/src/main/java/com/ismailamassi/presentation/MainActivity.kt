package com.ismailamassi.presentation

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.ismailamassi.domain.utils.ConnectionLiveData
import com.ismailamassi.presentation.databinding.ActivityMainBinding
import com.ismailamassi.presentation.utils.AppLanguage
import com.ismailamassi.presentation.utils.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var appBarConfiguration: AppBarConfiguration

    private var isFirstTime = true
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initTheme()
        val connectionLiveData = ConnectionLiveData(application)
        connectionLiveData.observe(this) { isConnected ->
            this.isConnected = isConnected
            if (!isFirstTime) {
                if (isConnected) backOnlineLabel() else noConnection()
            }
            isFirstTime = false
        }

        observeLiveData()
    }

    private fun initTheme() {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val currentThemeLabel = prefs.getString("key_app_theme", "system")
        val currentTheme = AppTheme.getThemeByLabel(currentThemeLabel ?: "system")

        changeAppTheme(currentTheme)
    }

    fun configAppBar() {
        val toolbar = mainBinding.appBarMain.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.show()

        val bottomNav = mainBinding.appBarMain.mainContent.bottomNavigationView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.nav_homeFragment,
                R.id.nav_favouriteFragment,
                R.id.nav_searchFragment,
                R.id.nav_tipsListFragment,
                R.id.nav_moreFragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNav.setupWithNavController(navController)
    }

    fun showBottomNavigationView() {
        mainBinding.appBarMain.mainContent.bottomNavigationView.visibility = View.VISIBLE
    }

    fun hideToolbarAndBottomNavigation() {
        hideToolbar()
        hideBottomNavigation()
    }

    fun hideToolbar(){
        mainBinding.appBarMain.toolbar.visibility = View.GONE

    }

    private fun hideBottomNavigation(){
        mainBinding.appBarMain.mainContent.bottomNavigationView.visibility = View.GONE

    }

    private fun observeLiveData() {
        mainViewModel.loadingLiveData.observe(this) {
            Timber.tag(TAG).d("observeLiveData : loadingLiveData $it")
        }

        mainViewModel.errorLiveData.observe(this) {
            Timber.tag(TAG).d("observeLiveData : errorLiveData ${it.message}")
        }
    }

    private fun backOnlineLabel() {
        //Show Connection Restore View for 3 sec then hide
        lifecycleScope.launch {
            mainBinding.appBarMain.mainContent.tvConnectionStatus.apply {
                visibility = View.VISIBLE
                text = getString(R.string.back_online)
                setBackgroundColor(resources.getColor(R.color.green))
                setTextColor(resources.getColor(R.color.white))
                delay(3000)
                visibility = View.GONE
            }
        }
    }

    fun changeAppbarTitle(appbarTitle: String) {
        supportActionBar?.title = appbarTitle
    }

    private fun noConnection() {
        //Show Connection Lose for 3 sec then hide
        lifecycleScope.launch {
            mainBinding.appBarMain.mainContent.tvConnectionStatus.apply {
                visibility = View.VISIBLE
                text = getString(R.string.no_connection)
                setBackgroundColor(resources.getColor(R.color.black))
                setTextColor(resources.getColor(R.color.white))
                delay(3000)
                visibility = View.GONE
            }
        }
    }

    fun changeAppTheme(appTheme: AppTheme, configAppBar: Boolean = false) {
        when (appTheme) {
            AppTheme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            AppTheme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppTheme.NIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    fun changeAppLanguage(appLanguage: AppLanguage) {

    }

    fun isConnected() = isConnected

    fun hideStatusBar() {
        /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
             window.decorView.windowInsetsController!!.hide(
                 WindowInsets.Type.statusBars()
             )
         } else {
             window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
         }*/
    }

    fun loginAsGuest() {
        val menu = mainBinding.navView.menu
        menu.findItem(R.id.nav_addEditCategoryFragment).isVisible = false
        menu.findItem(R.id.nav_addEditRecipeFragment).isVisible = false
        menu.findItem(R.id.nav_addEditTipFragment).isVisible = false

        menu.findItem(R.id.nav_loginFragment).isVisible = true
        menu.findItem(R.id.nav_splashFragment).isVisible = false
    }

    fun loginAsUser() {
        val menu = mainBinding.navView.menu
        menu.findItem(R.id.nav_addEditCategoryFragment).isVisible = true
        menu.findItem(R.id.nav_addEditRecipeFragment).isVisible = true
        menu.findItem(R.id.nav_addEditTipFragment).isVisible = true

        menu.findItem(R.id.nav_loginFragment).isVisible = false
        menu.findItem(R.id.nav_splashFragment).isVisible = true
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideStatusBar()
    }

    fun showStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController!!.show(
                WindowInsets.Type.statusBars()
            )
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Timber.tag(TAG).d("onBackPressed : ")
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        private const val TAG = "MainActivity"
    }


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        /*// TODO: 8/12/2021 Not Called
        val destinationsHideActionBar = listOf(
            R.id.nav_splashFragment,
            R.id.nav_onBoardingFragment,
            R.id.nav_loginFragment
        )
        val hideAppBar = destinationsHideActionBar.contains(destination.id)
        val lockMode =
            if (hideAppBar) DrawerLayout.LOCK_MODE_UNLOCKED else DrawerLayout.LOCK_MODE_LOCKED_CLOSED
        mainBinding.drawerLayout.setDrawerLockMode(lockMode)
        if (hideAppBar) {
            supportActionBar?.hide()
        } else {
            supportActionBar?.show()
        }*/
    }
}