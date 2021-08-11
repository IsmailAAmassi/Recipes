package com.ismailamassi.presentation.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.ismailamassi.domain.utils.ConnectionLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber


abstract class BaseFragment : Fragment() {
    val connectivity = MutableLiveData(false)

    private val connectivityLiveData: ConnectionLiveData by lazy {
        ConnectionLiveData(requireActivity().application)
    }

    abstract fun setup()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()

        connectivityLiveData.observe(viewLifecycleOwner){
            Timber.tag(TAG).d("setup : connectivityLiveData $it")
            connectivity.value  = it
        }
    }

    fun doOnUI(func: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch { func() }
    }

    fun doOnIO(func: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch { func() }
    }

    companion object {
        const val TAG = "BaseFragment"
    }
}