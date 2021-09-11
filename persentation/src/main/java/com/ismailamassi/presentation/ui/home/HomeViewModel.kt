package com.ismailamassi.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.repository.CategoryRepository
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val recipeRepository: RecipeRepository
) : BaseViewModel<HomeEvent>() {

    val categoriesLiveData = MutableLiveData<List<CategoryDto>?>()
    val recipesLiveData = MutableLiveData<List<RecipeDto>?>()

    override fun onTriggerEvent(eventType: HomeEvent) {
        when (eventType) {
            HomeEvent.GetCategories -> getCategories()
            HomeEvent.GetHomeRecipes -> getHomeRecipes()
        }
    }

    private fun getHomeRecipes() {
        viewModelScope.launch {
            recipeRepository.getMostViewedRecipes().map {
                when (it) {
                    DataState.Empty -> {
                        _loadingLiveData.postValue(false)
                        _emptyLiveData.postValue("No Recipes")
                    }
                    is DataState.Error -> {
                        _loadingLiveData.postValue(false)
                        _errorLiveData.postValue(it.exception)
                    }
                    DataState.Loading -> {
                        _loadingLiveData.postValue(true)
                    }
                    is DataState.Success -> {
                        _loadingLiveData.postValue(false)
                        recipesLiveData.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            categoryRepository.getAll().map {
                when (it) {
                    DataState.Empty -> {
                        _loadingLiveData.postValue(false)
                        _emptyLiveData.postValue("No Categories")
                    }
                    is DataState.Error -> {
                        _loadingLiveData.postValue(false)
                        _errorLiveData.postValue(it.exception)
                    }
                    DataState.Loading -> {
                        _loadingLiveData.postValue(true)
                    }
                    is DataState.Success -> {
                        _loadingLiveData.postValue(false)
                        categoriesLiveData.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}