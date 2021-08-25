package com.ismailamassi.presentation.ui.recipes_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : BaseViewModel<RecipesListEvent>() {

    val categoryRecipes = MutableLiveData<List<RecipeDto>?>()

    override fun onTriggerEvent(eventType: RecipesListEvent) {
        when (eventType) {
            is RecipesListEvent.GetCategoryRecipes -> getCategoryRecipes(eventType)
        }
    }

    private fun getCategoryRecipes(eventType: RecipesListEvent.GetCategoryRecipes) {
        Timber.tag(TAG).d("getCategoryRecipes : eventType.categoryId ${eventType.categoryId}")
        viewModelScope.launch {
            recipeRepository.getCategoryRecipes(eventType.categoryId).map {
                when (it) {
                    DataState.Empty -> {
                        _loadingLiveData.postValue(false)
                        _emptyLiveData.postValue("")
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
                        categoryRecipes.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}