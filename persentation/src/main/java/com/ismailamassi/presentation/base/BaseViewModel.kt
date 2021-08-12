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

    protected val _errorLiveData = MutableLiveData<Exception>()
    val errorLiveData: LiveData<Exception> get() = _errorLiveData

    protected val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    protected val _emptyLiveData = MutableLiveData<String>()
    val emptyLiveData: LiveData<String> get() = _emptyLiveData

    protected val _messageLiveData = MutableLiveData<String>()
    val messageLiveData: LiveData<String> get() = _messageLiveData

    protected val _eventStateLiveData = MutableLiveData<EventState>()
    val eventStateLiveData: LiveData<EventState> get() = _eventStateLiveData


    private var _event: BaseEvent? = null

    protected val event: T
        get() = _event as T

    abstract fun onTriggerEvent(eventType: T)

    companion object {
        const val TAG = "BaseViewModel"
    }

}