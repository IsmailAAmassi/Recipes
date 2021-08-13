package com.ismailamassi.presentation.ui.recipes_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentRecipesListBinding

class RecipesListFragment : BaseFragment<FragmentRecipesListBinding>(),
    View.OnClickListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipesListBinding
        get() = FragmentRecipesListBinding::inflate

    override fun setup() {
        val categoryId = arguments?.run { RecipesListFragmentArgs.fromBundle(this).categoryId }
        if (categoryId == null || categoryId < 0) {
            showSnackBar("Error In category Id", "Back") {
                findNavController().popBackStack()
            }
        } else {
            val category = fakeCategories.find { it.id == categoryId }
            if (category != null) {
                if (category.title != null) {
                    (requireActivity() as MainActivity).changeAppbarTitle(category.title ?: "")
                }
                val recipesList = fakeRecipes.filter { it.categoryId == categoryId }
                showToast("This Category Have ${recipesList.size}")
            } else {
                showSnackBar("Error In category Id", "Back") {
                    findNavController().popBackStack()
                }
            }
        }
    }

    override fun onClick(v: View?) {
    }

}