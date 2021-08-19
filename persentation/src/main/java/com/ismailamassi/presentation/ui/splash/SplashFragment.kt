package com.ismailamassi.presentation.ui.splash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ismailamassi.domain.Constants
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.MainViewModel
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentSplashBinding
import com.ismailamassi.presentation.utils.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(), View.OnClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    private val splashViewModel: SplashViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun setup() {
        splashViewModel.onTriggerEvent(SplashEvent.GetSettings)
        splashViewModel.onTriggerEvent(SplashEvent.UpdateDatabase)

        observeLiveData()
    }

    private fun observeLiveData() {

        splashViewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let { mainViewModel.showError(it) }
        }

        splashViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            it?.let { mainViewModel.showLoading(it) }
        }

        splashViewModel.emptyLiveData.observe(viewLifecycleOwner) {
            it?.let { Timber.tag(TAG).d("Empty $it") }
        }

        splashViewModel.messageLiveData.observe(viewLifecycleOwner) {
            it?.let { Timber.tag(TAG).d("Message $it") }
        }


        splashViewModel.settingsLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Timber.tag(TAG).d("observeLiveData : settingsLiveData currentUserId ${it.currentUserId}")
                Timber.tag(TAG).d("observeLiveData : settingsLiveData GUEST_USER_ID ${Constants.GUEST_USER_ID}")
                if (it.currentUserId == Constants.GUEST_USER_ID) {
                    (requireActivity() as MainActivity).loginAsGuest()
                } else {
                    (requireActivity() as MainActivity).loginAsUser()
                }
                val appTheme = AppTheme.getThemeById(it.theme ?: 0)
                (requireActivity() as MainActivity).changeAppTheme(appTheme)
                splashViewModel.settingsLiveData.postValue(null)
            }
        }

        splashViewModel.randomTipLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Timber.tag(TAG).d("observeLiveData : randomTipLiveData $it")
                binding.tvTipTitle.text = it.label
                splashViewModel.randomTipLiveData.postValue(null)
            }
        }

        splashViewModel.updateDataBaseLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Timber.tag(TAG).d("observeLiveData : updateDataBaseLiveData $it")
                if (it) findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                splashViewModel.updateDataBaseLiveData.postValue(null)
            }
        }
    }

    private fun moveToNextUI() {

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

    override fun onClick(v: View?) {

    }


    companion object {
        private const val TAG = "SplashFragment"
    }
}