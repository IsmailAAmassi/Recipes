package com.ismailamassi.presentation.ui.add_edit_recipe.recipe_steps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipeIngredientsBinding
import com.ismailamassi.presentation.databinding.FragmentRecipeStepsBinding


class RecipeStepsFragment  : BaseFragment<FragmentRecipeStepsBinding>(),View.OnClickListener  {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeStepsBinding
        get() = FragmentRecipeStepsBinding::inflate

    override fun setup() {
    }

    override fun onClick(v: View?) {
    }

}