package com.ismailamassi.presentation.ui.recipes_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipesListBinding
import com.ismailamassi.presentation.ui.home.RecipeListType
import com.ismailamassi.presentation.ui.recipes_list.adapter.RecipeListListener
import com.ismailamassi.presentation.ui.recipes_list.adapter.RecipesListAdapter
import timber.log.Timber

class RecipesListFragment : BaseFragment<FragmentRecipesListBinding>(),
    View.OnClickListener, RecipeListListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipesListBinding
        get() = FragmentRecipesListBinding::inflate

    private val recipesListAdapter: RecipesListAdapter by lazy { RecipesListAdapter(this) }

    override fun setup() {
        //init Default App Bar
        (requireActivity() as MainActivity).configAppBar()

        val recipesType =
            arguments?.run { RecipesListFragmentArgs.fromBundle(this).recipesType }
        var category: CategoryDto? = null
        val recipesList = when (recipesType) {
            RecipeListType.BestCollectionRecipes -> {
                // TODO: 8/20/2021 Get result from API
                (requireActivity() as MainActivity).changeAppbarTitle("Best Collection")
                fakeRecipes
            }
            is RecipeListType.CategoryRecipes -> {
                // TODO: 8/20/2021 Get result from DB
                category = fakeCategories.find { it.id == recipesType.categoryId }
                (requireActivity() as MainActivity).changeAppbarTitle(category?.title?:"Category")
                fakeRecipes.filter { it.categoryId == recipesType.categoryId }
            }
            RecipeListType.MostLovedRecipes -> {
                (requireActivity() as MainActivity).changeAppbarTitle("Most Loved Recipes")
                // TODO: 8/20/2021 Get result from API
                fakeRecipes
            }
            RecipeListType.MostViewedRecipes -> {
                (requireActivity() as MainActivity).changeAppbarTitle("Most Viewed Recipes")
                // TODO: 8/20/2021 Get result from API
                fakeRecipes
            }
            null -> {
                // TODO: 8/20/2021 Return Empty Result with error message
                listOf()
            }
        }


        if (recipesList.isEmpty()) {
            // TODO: 8/14/2021 Show Empty Status
            changeEmptyStatusVisibility(true)
        } else {
            changeEmptyStatusVisibility(false)
            initData(recipesList)
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