package com.ismailamassi.presentation.ui.recipe_info

import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.MainViewModel
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipeInfoBackupBinding
import com.ismailamassi.presentation.ui.recipe_info.adapters.RecipeInfoIngredientAdapter
import com.ismailamassi.presentation.ui.recipe_info.adapters.RecipeInfoStepAdapter
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RecipeInfoFragment : BaseFragment<FragmentRecipeInfoBackupBinding>(),
    View.OnClickListener {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: RecipeViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeInfoBackupBinding
        get() = FragmentRecipeInfoBackupBinding::inflate

    var recipeId: Long = -1


    private val ingredientsAdapter: RecipeInfoIngredientAdapter by lazy {
        RecipeInfoIngredientAdapter()
    }

    private val stepsAdapter: RecipeInfoStepAdapter by lazy {
        RecipeInfoStepAdapter()
    }

    override fun setup() {

        binding.apply {
            onClickListener = this@RecipeInfoFragment
            //Start animation then disable it
            mlRecipeInfo.transitionToEnd()
            mlRecipeInfo.getTransition(R.id.transitionHome).isEnabled = false
        }

        recipeId = arguments?.run { RecipeInfoFragmentArgs.fromBundle(this).recipeId } ?: -1

        viewModel.onTriggerEvent(RecipeInfoEvent.GetRecipeInfo(recipeId = recipeId))

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.recipeInfoLiveData.observe(viewLifecycleOwner) { recipeDto ->
            if (recipeDto != null) {
                binding.apply {
                    tvPreparationTimeLabel.isSelected = true
                    tvCookingTimeLabel.isSelected = true
                    tvServingLabel.isSelected = true
                    tvRecipeInfoName.isSelected = true

                    rvRecipeInfoIngredients.addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            RecyclerView.VERTICAL
                        )
                    )
                }
                initData(recipeDto)
            }
        }

        viewModel.favoriteStatusLiveData.observe(viewLifecycleOwner) {
            it?.let {
                if (it) {
                    //Set Icon Filled
                    binding.ibFavouriteRecipe.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.fav_enable,
                            context?.theme
                        )
                    )
                } else {
                    //Set Icon Outline
                    binding.ibFavouriteRecipe.setImageDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.fav_disable,
                            context?.theme
                        )
                    )
                }
            }
        }

        viewModel.emptyLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Timber.tag(TAG).d("observeLiveData : $it")
            }
        }

        viewModel.messageLiveData.observe(viewLifecycleOwner) {
            it?.let {
                showSnackBar(it)
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                mainViewModel.showError(it)
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            it?.let {
                mainViewModel.showLoading(it)
            }
        }
    }

    private fun initData(recipeDto: RecipeDto) {

        binding.apply {
            this.recipe = recipeDto
            this.ingredientsAdapter = this@RecipeInfoFragment.ingredientsAdapter
            this.stepsAdapter = this@RecipeInfoFragment.stepsAdapter
        }

        ingredientsAdapter.update(recipeDto.ingredients ?: listOf())
        stepsAdapter.update((recipeDto.steps?.sortedBy { it.order } ?: listOf()))
    }


    override fun onClick(v: View?) {
        binding.apply {
            when (v) {
                ibToolbarBack -> findNavController().popBackStack()
                ibFavouriteRecipe -> changeFavoriteStatus()
            }
        }
    }

    private fun changeFavoriteStatus() {
        val currentStatus = viewModel.favoriteStatusLiveData.value!!
        if (currentStatus) {
            //Delete from favourite table
            viewModel.onTriggerEvent(
                RecipeInfoEvent.ChangeFavouriteStatus(
                    recipeId = recipeId,
                    isFavourite = false
                )
            )
        } else {
            //Create in favourite table
            viewModel.onTriggerEvent(
                RecipeInfoEvent.ChangeFavouriteStatus(
                    recipeId = recipeId,
                    isFavourite = true
                )
            )
        }
    }

}