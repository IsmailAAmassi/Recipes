package com.ismailamassi.presentation.ui.categories_list

import android.view.*
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
import com.ismailamassi.presentation.utils.RecyclerMood
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoriesListFragment : BaseFragment<FragmentCategoriesListBinding>(), View.OnClickListener,
    CategoriesListListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoriesListBinding
        get() = FragmentCategoriesListBinding::inflate

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: CategoriesListViewModel by viewModels()

    private lateinit var menu: Menu

    private var currentRecyclerMood = RecyclerMood.TWO_SPAN

    private val categoriesListAdapter: CategoriesListAdapter by lazy {
        CategoriesListAdapter(this)
    }

    override fun setup() {
        binding.apply {
            ibCategoriesListVertical.setColorFilter(R.color.white)
        }
        setHasOptionsMenu(true)
        binding.apply {
            onClickListener = this@CategoriesListFragment
            categoriesListAdapter = this@CategoriesListFragment.categoriesListAdapter
        }
        categoriesListAdapter.update(fakeCategories)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        this.menu = menu
        inflater.inflate(R.menu.categories_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_changeRecyclerMood -> {
                changeRecyclerMood()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun changeRecyclerMood() {
        currentRecyclerMood = if (currentRecyclerMood == RecyclerMood.TWO_SPAN) {
            menu.findItem(R.id.mi_changeRecyclerMood).setIcon(R.drawable.ic_vertical_bar)
            onClickShowGrid()
            RecyclerMood.THREE_SPAN
        } else {
            menu.findItem(R.id.mi_changeRecyclerMood).setIcon(R.drawable.ic_visualization)
            onClickShowVertical()
            RecyclerMood.TWO_SPAN
        }
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

    }

}