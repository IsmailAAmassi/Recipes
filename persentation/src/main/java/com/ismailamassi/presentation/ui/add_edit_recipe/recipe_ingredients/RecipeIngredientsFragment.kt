package com.ismailamassi.presentation.ui.add_edit_recipe.recipe_ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipeIngredientsBinding

class RecipeIngredientsFragment : BaseFragment<FragmentRecipeIngredientsBinding>(),
    View.OnClickListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeIngredientsBinding
        get() = FragmentRecipeIngredientsBinding::inflate

    override fun setup() {
    }

    override fun onClick(v: View?) {
    }

}