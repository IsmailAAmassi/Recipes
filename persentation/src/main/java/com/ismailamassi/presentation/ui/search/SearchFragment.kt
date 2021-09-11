package com.ismailamassi.presentation.ui.search

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.MainViewModel
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import timber.log.Timber

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(), SearchResultListener {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: SearchViewModel by viewModels()

    private val searchResultsAdapter: SearchResultsAdapter by lazy {
        SearchResultsAdapter(this)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    override fun setup() {

        binding.apply {
            searchResultsAdapter = this@SearchFragment.searchResultsAdapter
            etSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Timber.tag(TAG).d("onTextChanged : ttt s $s")
                    handleNoSearchQuery()
                    if (s.isNullOrBlank() || s.isNullOrEmpty()) {
                        handleNoSearchQuery()
                    } else {
                        viewModel.onTriggerEvent(SearchEvent.SearchQuery(s.toString()))
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })
            etSearch.addTextChangedListener {

            }
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.recipesResult.observe(viewLifecycleOwner) {
            it?.let { recipes ->
                handleSearchResults(recipes)
            }
        }

        viewModel.emptyLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Timber.tag(TAG).d("observeLiveData : $it")
                handleNoSearchResults()
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                mainViewModel.showError(it)
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            it?.let {
                showSearching(it)
            }
        }
    }

    private fun showSearching(it: Boolean) {
        binding.flSearching.visibility = if (it) View.VISIBLE else View.GONE
    }

    private fun handleNoSearchQuery() {
        Timber.tag(TAG).d("handleNoSearchQuery : ttt ")
        binding.clSearchNoResults.visibility = View.GONE
        searchResultsAdapter.update(emptyList())
    }

    private fun handleSearchResults(recipes: List<RecipeDto>) {
        binding.clSearchNoResults.visibility = View.GONE
        searchResultsAdapter.update(recipes)
    }

    private fun handleNoSearchResults() {
        binding.clSearchNoResults.visibility = View.VISIBLE
        searchResultsAdapter.update(emptyList())
    }

    override fun onClickSearchResult(recipeId: Long) {
        findNavController().navigate(
            SearchFragmentDirections.actionNavSearchFragmentToNavRecipeInfoFragment(
                recipeId = recipeId
            )
        )
    }

}