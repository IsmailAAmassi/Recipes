package com.ismailamassi.presentation.ui.recipes_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.MainViewModel
import com.ismailamassi.presentation.R
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

        val title = when (val recipesType =
            arguments?.run { RecipesListFragmentArgs.fromBundle(this).recipesType }) {
            RecipeListType.BestCollectionRecipes -> {
                viewModel.onTriggerEvent(RecipesListEvent.GetBestCollection)
                getString(R.string.best_collection)
            }
            is RecipeListType.CategoryRecipes -> {
                viewModel.onTriggerEvent(RecipesListEvent.GetCategoryRecipes(categoryId = recipesType.categoryId))
                ""
            }
            RecipeListType.MostLovedRecipes -> {
                viewModel.onTriggerEvent(RecipesListEvent.GetMostLoved)
                getString(R.string.most_loved_recipes)
            }
            RecipeListType.MostViewedRecipes -> {
                viewModel.onTriggerEvent(RecipesListEvent.GetMostViewed)
                getString(R.string.recipes_might_you_like)
            }
            null -> {
                ""
            }
        }
        Timber.tag(TAG).d("setup : title $title")
        binding.toolbar.title = title


        binding.toolbar.ibToolbarBack.setOnClickListener {
            findNavController().popBackStack()
        }

        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.category.observe(viewLifecycleOwner) {
            it?.let {
                binding.toolbar.title = it.title
            }
        }

        viewModel.recipes.observe(viewLifecycleOwner) {
            it?.let { recipesList ->
                if (recipesList.isEmpty()) {
                    changeEmptyStatusVisibility(true)
                } else {
                    changeEmptyStatusVisibility(false)
                    initData(recipesList)
                }
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let { mainViewModel.showError(it) }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            it?.let { mainViewModel.showLoading(it) }
        }
    }


    private fun changeEmptyStatusVisibility(visibility: Boolean) {
        Timber.tag(TAG).d("changeEmptyStatusVisibility : visibility $visibility")
        binding.apply {
            if (visibility) {
                clEmptyStatus.visibility = View.VISIBLE
                rvRecipesList.visibility = View.GONE
            } else {
                clEmptyStatus.visibility = View.GONE
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
        findNavController().navigate(
            RecipesListFragmentDirections.actionNavRecipesListFragmentToNavRecipeInfoFragment(
                recipeId = recipeId
            )
        )
    }

}