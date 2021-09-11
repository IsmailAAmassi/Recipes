package com.ismailamassi.presentation.ui.add_edit_recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentAddEditRecipeBinding
import com.ismailamassi.presentation.databinding.FragmentRecipeIngredientsBinding

class AddEditRecipeFragment : BaseFragment<FragmentAddEditRecipeBinding>(),
    View.OnClickListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddEditRecipeBinding
        get() = FragmentAddEditRecipeBinding::inflate

    override fun setup() {
    }

    override fun onClick(v: View?) {
    }

}