package com.ismailamassi.presentation.ui.recipes_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipesListBinding
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

        val categoryId = arguments?.run { RecipesListFragmentArgs.fromBundle(this).categoryId } ?: -1
        val category = fakeCategories.find { it.id == categoryId }
        val recipesList = fakeRecipes.filter { it.categoryId == category?.id }
        (requireActivity() as MainActivity).changeAppbarTitle(category?.title ?: "Category")

        if (categoryId == -1L || category == null || recipesList.isEmpty()) {
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