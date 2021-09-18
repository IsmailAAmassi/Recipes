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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(){

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSplashBinding
        get() = FragmentSplashBinding::inflate

    private val splashViewModel: SplashViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun setup() {
        splashViewModel.onTriggerEvent(SplashEvent.UpdateDatabase)

        observeLiveData()

        moveToNextUI()
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
    }

    private fun moveToNextUI() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            try{
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }catch (e:Exception){
            }
        }
    }

    override fun onResume() {
        super.onResume()
        moveToNextUI()
    }

    companion object {
        private const val TAG = "SplashFragment"
    }
}