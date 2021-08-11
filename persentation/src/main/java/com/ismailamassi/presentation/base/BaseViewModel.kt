package com.ismailamassi.presentation.base

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ismailamassi.domain.utils.ConnectionLiveData
import com.kordia.achievements.domain.utils.EventState
import javax.inject.Inject

@Suppress("PropertyName", "UNCHECKED_CAST")
abstract class BaseViewModel<T> : ViewModel() {

    @Inject
    lateinit var application: Application

    val loading = MutableLiveData(false)
    val error = MutableLiveData(Exception())
    val message = MutableLiveData(String())

    protected val _eventStateLiveData = MutableLiveData<EventState>()
    val eventStateLiveData: LiveData<EventState> get() = _eventStateLiveData

    private var _event: BaseEvent? = null

    protected val event: T
        get() = _event as T

    abstract fun onTriggerEvent(eventType: T)

    companion object {
        const val TAG = "BaseViewModel"
    }

    private val connectivityLiveData: ConnectionLiveData by lazy {
        ConnectionLiveData(application)
    }
}