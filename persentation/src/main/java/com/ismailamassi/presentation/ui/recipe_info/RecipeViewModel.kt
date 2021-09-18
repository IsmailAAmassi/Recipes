package com.ismailamassi.presentation.ui.recipe_info

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.favourite.FavouriteDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.repository.FavouriteRepository
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val recipeRepository: RecipeRepository,
    private val favouriteRepository: FavouriteRepository
) : BaseViewModel<RecipeInfoEvent>() {

    val recipeInfoLiveData = MutableLiveData<RecipeDto?>()
    val favoriteStatusLiveData = MutableLiveData<Boolean?>()

    override fun onTriggerEvent(eventType: RecipeInfoEvent) {
        when (eventType) {
            is RecipeInfoEvent.GetRecipeInfo -> getRecipeInfo(eventType)
            is RecipeInfoEvent.ChangeFavouriteStatus -> onChangeFavouriteStatus(eventType)
        }
    }

    private fun onChangeFavouriteStatus(eventType: RecipeInfoEvent.ChangeFavouriteStatus) {
        viewModelScope.launch {
            val recipeId = eventType.recipeId
            val favouriteStatus = eventType.isFavourite
            (if (favouriteStatus) {
                favouriteRepository.create(favouriteDto = FavouriteDto(recipeId, 0L))
            } else {
                favouriteRepository.delete(recipeId)
            }).map {
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
                        val message =
                            if (favouriteStatus) context.getString(R.string.add_successfully_to_fav) else context.getString(
                                R.string.removed_successfully_from_fav
                            )
                        _messageLiveData.postValue(message)
                        favoriteStatusLiveData.postValue(eventType.isFavourite)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun getRecipeInfo(eventType: RecipeInfoEvent.GetRecipeInfo) {
        viewModelScope.launch {
            val recipeId = eventType.recipeId
            recipeRepository.getFullRecipe(recipeId).map {
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
            favouriteRepository.isRecipeFavourite(recipeId).map {
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
                        favoriteStatusLiveData.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}