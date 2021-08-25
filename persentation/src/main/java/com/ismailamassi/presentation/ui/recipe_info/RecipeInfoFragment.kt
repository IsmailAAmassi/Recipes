package com.ismailamassi.presentation.ui.recipe_info

import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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


    private var menu: Menu? = null

    private val ingredientsAdapter: RecipeInfoIngredientAdapter by lazy {
        RecipeInfoIngredientAdapter()
    }

    private val stepsAdapter: RecipeInfoStepAdapter by lazy {
        RecipeInfoStepAdapter()
    }

    override fun setup() {

        val recipeId = arguments?.run { RecipeInfoFragmentArgs.fromBundle(this).recipeId } ?: -1

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

        viewModel.emptyLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Timber.tag(TAG).d("observeLiveData : $it")

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        this.menu = menu
        inflater.inflate(R.menu.recipet_info_menu, menu)
    }

    private fun initData(recipeDto: RecipeDto) {
        Timber.tag(TAG).d("initData : RecipeInfoFragment recipeDto $recipeDto")
        binding.apply {
            this.recipe = recipeDto
            this.ingredientsAdapter = this@RecipeInfoFragment.ingredientsAdapter
            this.stepsAdapter = this@RecipeInfoFragment.stepsAdapter
        }

        ingredientsAdapter.update(recipeDto.ingredients ?: listOf())
        stepsAdapter.update((recipeDto.steps?.sortedBy { it.order } ?: listOf()))
    }


    override fun onClick(v: View?) {
    }

}