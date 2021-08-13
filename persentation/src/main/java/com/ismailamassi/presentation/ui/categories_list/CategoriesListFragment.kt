package com.ismailamassi.presentation.ui.categories_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ismailamassi.presentation.MainViewModel
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentCategoriesListBinding
import com.ismailamassi.presentation.ui.categories_list.adapter.CategoriesListAdapter
import com.ismailamassi.presentation.ui.categories_list.adapter.CategoriesListListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoriesListFragment : BaseFragment<FragmentCategoriesListBinding>(), View.OnClickListener,
    CategoriesListListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoriesListBinding
        get() = FragmentCategoriesListBinding::inflate

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: CategoriesListViewModel by viewModels()

    private val categoriesListAdapter: CategoriesListAdapter by lazy {
        CategoriesListAdapter(this)
    }

    override fun setup() {
        binding.apply {
            ibCategoriesListVertical.setColorFilter(R.color.white)
        }

        binding.apply {
            onClickListener = this@CategoriesListFragment
            categoriesListAdapter = this@CategoriesListFragment.categoriesListAdapter
        }
        categoriesListAdapter.update(fakeCategories)
    }


    override fun onClick(v: View?) {
        binding.run {
            when (v) {
                ibCategoriesListVertical -> onClickShowVertical()
                ibCategoriesListGrid -> onClickShowGrid()
            }
        }
    }

    private fun onClickShowVertical() {
        binding.ibCategoriesListVertical.setColorFilter(R.color.white)
        binding.ibCategoriesListGrid.clearColorFilter()
        changeSpanCount(2)
    }

    private fun onClickShowGrid() {
        binding.ibCategoriesListGrid.setColorFilter(R.color.white)
        binding.ibCategoriesListVertical.clearColorFilter()
        changeSpanCount(3)
    }

    private fun changeSpanCount(spanCount: Int) {
        (binding.rvCategoriesList.layoutManager as GridLayoutManager).spanCount = spanCount
    }

    override fun onClickCategory(categoryId: Long) {
        findNavController().navigate(
            CategoriesListFragmentDirections.actionNavCategoriesListFragmentToNavRecipesListFragment(
                categoryId = categoryId
            )
        )
    }

}