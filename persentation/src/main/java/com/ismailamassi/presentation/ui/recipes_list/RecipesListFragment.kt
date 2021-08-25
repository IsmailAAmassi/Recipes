package com.ismailamassi.presentation.ui.recipes_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.MainViewModel
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipesListBinding
import com.ismailamassi.presentation.ui.home.RecipeListType
import com.ismailamassi.presentation.ui.recipes_list.adapter.RecipeListListener
import com.ismailamassi.presentation.ui.recipes_list.adapter.RecipesListAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RecipesListFragment : BaseFragment<FragmentRecipesListBinding>(),
    View.OnClickListener, RecipeListListener {


    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: RecipesListViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipesListBinding
        get() = FragmentRecipesListBinding::inflate

    private val recipesListAdapter: RecipesListAdapter by lazy { RecipesListAdapter(this) }

    override fun setup() {

        val recipesType = arguments?.run { RecipesListFragmentArgs.fromBundle(this).recipesType }
        viewModel.onTriggerEvent(RecipesListEvent.GetCategoryRecipes((recipesType as RecipeListType.CategoryRecipes).categoryId))
//        var category: CategoryDto? = null
        /*val recipesList = when (recipesType) {
            RecipeListType.BestCollectionRecipes -> {
                fakeRecipes
            }
            is RecipeListType.CategoryRecipes -> {
                category = fakeCategories.find { it.id == recipesType.categoryId }

            }
            RecipeListType.MostLovedRecipes -> {
                fakeRecipes
            }
            RecipeListType.MostViewedRecipes -> {
                // TODO: 8/20/2021 Get result from API
                fakeRecipes
            }
            null -> {
                // TODO: 8/20/2021 Return Empty Result with error message
                listOf()
            }
        }*/

        binding.ibToolbarBack.setOnClickListener {
            findNavController().popBackStack()
        }
        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.categoryRecipes.observe(viewLifecycleOwner) {
            it?.let { recipesList ->
                if (recipesList.isEmpty()) {
                    changeEmptyStatusVisibility(true)
                } else {
                    changeEmptyStatusVisibility(false)
                    initData(recipesList)
                }
            }
        }
    }

    private fun changeEmptyStatusVisibility(visibility: Boolean) {
        Timber.tag(TAG).d("changeEmptyStatusVisibility : visibility $visibility")
        binding.apply {
            if (visibility) {
                clRecipesListEmptyStatus.visibility = View.VISIBLE
                rvRecipesList.visibility = View.GONE
            } else {
                clRecipesListEmptyStatus.visibility = View.GONE
                rvRecipesList.visibility = View.VISIBLE
            }
        }
    }


    private fun initData(recipesList: List<RecipeDto>) {
        binding.recipeListAdapter = recipesListAdapter
        recipesListAdapter.update(recipesList)
    }

    override fun onClick(v: View?) {
    }

    override fun onClickRecipe(recipeId: Long) {
        showToast("Go To Recipe Details for $recipeId")
        findNavController().navigate(
            RecipesListFragmentDirections.actionNavRecipesListFragmentToNavRecipeInfoFragment(
                recipeId = recipeId
            )
        )
    }

}