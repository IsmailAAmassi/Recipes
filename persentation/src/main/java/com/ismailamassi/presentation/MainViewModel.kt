package com.ismailamassi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _loadingLiveData = MutableLiveData(false)
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData(Exception())
    val errorLiveData: LiveData<Exception> get() = _errorLiveData

    fun showLoading(isLoading: Boolean) = _loadingLiveData.postValue(isLoading)

    fun showError(error: Exception) = _errorLiveData.postValue(error)

}