package com.ismailamassi.presentation.ui.recipes_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.domain.repository.CategoryRepository
import com.ismailamassi.domain.repository.RecipeRepository
import com.ismailamassi.domain.utils.DataState
import com.ismailamassi.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val recipeRepository: RecipeRepository
) : BaseViewModel<RecipesListEvent>() {

    val category = MutableLiveData<CategoryDto?>()
    val recipes = MutableLiveData<List<RecipeDto>?>()

    override fun onTriggerEvent(eventType: RecipesListEvent) {
        when (eventType) {
            is RecipesListEvent.GetCategoryRecipes -> getCategoryRecipes(eventType)
            RecipesListEvent.GetBestCollection -> getBestCollection()
            RecipesListEvent.GetMostLoved -> getMostLoved()
            RecipesListEvent.GetMostViewed -> getMostViewed()
        }
    }

    private fun getMostViewed() {
        viewModelScope.launch {
            recipeRepository.getMostViewedRecipes()
                .map {
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
                            recipes.postValue(it.data)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun getMostLoved() {
        viewModelScope.launch {
            recipeRepository.getMostLovedRecipes()
                .map {
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
                            recipes.postValue(it.data)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun getBestCollection() {
        viewModelScope.launch {
            recipeRepository.getBestCollectionRecipes()
                .map {
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
                            recipes.postValue(it.data)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun getCategoryRecipes(eventType: RecipesListEvent.GetCategoryRecipes) {
        val categoryId = eventType.categoryId
        Timber.tag(TAG).d("getCategoryRecipes : categoryId $categoryId")
        viewModelScope.launch {
            categoryRepository.get(categoryId).collect {
                if (it is DataState.Success){
                    category.postValue(it.data)
                }
            }

            recipeRepository.getCategoryRecipes(categoryId).map {
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
                        recipes.postValue(it.data)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}