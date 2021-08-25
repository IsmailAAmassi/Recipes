package com.ismailamassi.presentation.ui.recipe_info

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
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : BaseViewModel<RecipeInfoEvent>() {

    val recipeInfoLiveData = MutableLiveData<RecipeDto?>()

    override fun onTriggerEvent(eventType: RecipeInfoEvent) {
        when (eventType) {
            is RecipeInfoEvent.GetRecipeInfo -> getRecipeInfo(eventType)
        }
    }

    private fun getRecipeInfo(eventType: RecipeInfoEvent.GetRecipeInfo) {
        viewModelScope.launch {
            recipeRepository.getFullRecipe(eventType.recipeId).map {
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
                        recipeInfoLiveData.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}