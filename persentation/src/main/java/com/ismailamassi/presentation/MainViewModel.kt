package com.ismailamassi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.model.recipe.StepDto
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.domain.repository.*
import com.ismailamassi.domain.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
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
            categoryRepository.getAllFromAPI().collect()
            recipeRepository.getAllFromAPI().collect()
            ingredientRepository.getAllFromAPI().collect()
            stepRepository.getAllFromAPI().collect()
            tipRepository.getAllFromAPI().collect()
        }
    }


}