package com.ismailamassi.presentation.ui.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.repository.FavouriteRepository
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val favouriteRepository: FavouriteRepository
) : BaseViewModel<FavouriteEvent>() {

    val favouritesLiveData = MutableLiveData<List<RecipeDto>?>()

    override fun onTriggerEvent(eventType: FavouriteEvent) {
        when (eventType) {
            FavouriteEvent.GetFavourites -> getFavourites()
        }
    }

    private fun getFavourites() {
        viewModelScope.launch {
            recipeRepository.getFavouritesRecipes().map {
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
                        favouritesLiveData.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}