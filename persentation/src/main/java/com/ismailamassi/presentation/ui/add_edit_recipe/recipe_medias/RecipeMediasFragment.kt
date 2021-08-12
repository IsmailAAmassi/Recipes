package com.ismailamassi.presentation.ui.add_edit_recipe.recipe_medias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipeIngredientsBinding


class RecipeMediasFragment  : BaseFragment<FragmentRecipeIngredientsBinding>(),View.OnClickListener  {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipeIngredientsBinding
        get() = FragmentRecipeIngredientsBinding::inflate

    override fun setup() {
    }

    override fun onClick(v: View?) {
    }

}