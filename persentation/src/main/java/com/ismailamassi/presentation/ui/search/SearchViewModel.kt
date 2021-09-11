package com.ismailamassi.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.repository.IngredientRepository
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val ingredientRepository: IngredientRepository
) : BaseViewModel<SearchEvent>() {

    val recipesResult = MutableLiveData<List<RecipeDto>?>()

    override fun onTriggerEvent(eventType: SearchEvent) {
        when (eventType) {
            is SearchEvent.SearchQuery -> onSearchQueryChanged(eventType)
        }
    }

    private fun onSearchQueryChanged(eventType: SearchEvent.SearchQuery) {
        viewModelScope.launch {
            recipeRepository.search(eventType.query).map {
                when (it) {
                    DataState.Empty -> {
                        _loadingLiveData.postValue(false)
                        _emptyLiveData.postValue("No Results Found")
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
                        recipesResult.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}