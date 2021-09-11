package com.ismailamassi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository,
    private val stepRepository: StepRepository,
    private val tipRepository: TipRepository
) : ViewModel() {

    private val _loadingLiveData = MutableLiveData(false)
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    private val _errorLiveData = MutableLiveData(Exception())
    val errorLiveData: LiveData<Exception> get() = _errorLiveData

    fun showLoading(isLoading: Boolean) = _loadingLiveData.postValue(isLoading)

    fun showError(error: Exception) = _errorLiveData.postValue(error)

    fun updateDatabase() {
        viewModelScope.launch {
            Timber.tag(TAG).d("updateDatabase : Start Sync")
            categoryRepository.syncTable().collect {
                Timber.tag(TAG).d("updateDatabase : Sync Category table status $it")
            }
            recipeRepository.syncTable().collect {
                Timber.tag(TAG).d("updateDatabase : Sync Recipe table status $it")
            }
            ingredientRepository.syncTable().collect {
                Timber.tag(TAG).d("updateDatabase : Sync Ingredient table status $it")
            }
            stepRepository.syncTable().collect {
                Timber.tag(TAG).d("updateDatabase : Sync Steps table status $it")
            }
            tipRepository.syncTable().collect {
                Timber.tag(TAG).d("updateDatabase : Sync Tips table status $it")
            }
            Timber.tag(TAG).d("updateDatabase : End Sync")

        }
    }

    companion object{
        private const val TAG = "MainViewModel"
    }

}