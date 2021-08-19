package com.ismailamassi.presentation.ui.recipe_info

import android.view.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipeInfoBinding
import com.ismailamassi.presentation.ui.recipe_info.adapters.RecipeInfoIngredientAdapter
import com.ismailamassi.presentation.ui.recipe_info.adapters.RecipeInfoStepAdapter
import timber.log.Timber

class RecipeInfoFragment : BaseFragment<FragmentRecipeInfoBinding>(),
    View.OnClickListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeInfoBinding
        get() = FragmentRecipeInfoBinding::inflate

    private var menu: Menu? = null

    private val ingredientsAdapter: RecipeInfoIngredientAdapter by lazy {
        RecipeInfoIngredientAdapter()
    }

    private val stepsAdapter: RecipeInfoStepAdapter by lazy {
        RecipeInfoStepAdapter()
    }

    override fun setup() {
//        (requireActivity() as MainActivity).supportActionBar?.hide()
//        (requireActivity() as MainActivity).setSupportActionBar(binding.toolbar)
//        (requireActivity() as MainActivity).supportActionBar?.show()

//        setHasOptionsMenu(true)

        /*  binding.appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
              var isShow = false
              var scrollRange = -1
              override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                  Timber.tag(TAG).d("onOffsetChanged : $verticalOffset")
                  if (scrollRange == -1) {
                      scrollRange = appBarLayout?.totalScrollRange ?: 0
                  }
                  if (scrollRange + verticalOffset == 0) {
                      isShow = true;
                      showOption(R.id.mi_shareRecipe)
                  } else if (isShow) {
                      isShow = false;
                      hideOption(R.id.mi_shareRecipe)
                  }
              }
          })*/

        binding.fragmentRecipeInfoContent.apply {
            rvRecipeInfoIngredients.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    RecyclerView.VERTICAL
                )
            )
        }

        val recipeId = arguments?.run { RecipeInfoFragmentArgs.fromBundle(this).recipeId } ?: -1
        val recipeDto = fakeRecipes.find { it.id == recipeId }

        if (recipeDto != null) {
            (requireActivity() as MainActivity).changeAppbarTitle(recipeDto.title ?: "Recipe")
            val ingredients = fakeIngredients.filter { it.recipeId == recipeDto.id }
            val steps = fakeSteps.filter { it.recipeId == recipeDto.id }
            initData(recipeDto.apply {
                this.ingredients = ingredients
                this.steps = steps
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        this.menu = menu
        inflater.inflate(R.menu.recipet_info_menu, menu);
        hideOption(R.id.mi_shareRecipe);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun initData(recipeDto: RecipeDto) {
        Timber.tag(TAG).d("initData : RecipeInfoFragment recipeDto $recipeDto")
        binding.fragmentRecipeInfoContent.apply {
            this.recipe = recipeDto
            this.ingredientsAdapter = this@RecipeInfoFragment.ingredientsAdapter
            this.stepsAdapter = this@RecipeInfoFragment.stepsAdapter
        }

        ingredientsAdapter.update(recipeDto.ingredients ?: listOf())
        stepsAdapter.update(recipeDto.steps ?: listOf())
    }


    private fun hideOption(id: Int) {
        Timber.tag(TAG).d("hideOption : ")
        val item: MenuItem? = menu?.findItem(id)
        item?.isVisible = false
    }

    private fun showOption(id: Int) {
        val item: MenuItem? = menu?.findItem(id)
        item?.isVisible = true
    }

    override fun onClick(v: View?) {
    }

}