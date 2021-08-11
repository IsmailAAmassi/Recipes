package com.ismailamassi.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private val splashViewModel: SplashViewModel by viewModels()

    private var splashBinding: FragmentSplashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).hideStatusBar()
        splashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return splashBinding?.apply {
            btnDark.setOnClickListener {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            btnLight.setOnClickListener {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }?.root
    }


    override fun setup() {
        splashViewModel.onTriggerEvent(SplashEvent.GetRandomTip)
        splashViewModel.onTriggerEvent(SplashEvent.GetSettings)
        splashViewModel.onTriggerEvent(SplashEvent.UpdateDatabase)
    }

    private fun moveToNextUI() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
        /*lifecycleScope.launch {
            delay(1500)

            val isLogin = splashViewModel.isLogin.value!!
            val isFirstTime = splashViewModel.isFirstTime.value!!

            when {
                isLogin -> {
                    //Go To Home

                }
                isFirstTime -> {
                    //Go To OnBoarding then to Sign Up
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment())
                }
                else -> {
                    //Go To Sign In
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToSignInFragment())
                }
            }
        }*/
    }
}