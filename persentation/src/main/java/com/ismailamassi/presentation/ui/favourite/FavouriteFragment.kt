package com.ismailamassi.presentation.ui.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ismailamassi.presentation.MainViewModel
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentFavouriteBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(), FavouritesListener {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: FavouriteViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteBinding
        get() = FragmentFavouriteBinding::inflate

    private val favouritesAdapter: FavouritesAdapter by lazy {
        FavouritesAdapter(this)
    }

    override fun setup() {

        binding.apply {
            favouritesAdapter = this@FavouriteFragment.favouritesAdapter

            toolbar.title = getString(R.string.favourites)

            toolbar.ibToolbarBack.visibility = View.GONE
            toolbar.ibPlaceHolder.visibility = View.GONE
        }

        viewModel.onTriggerEvent(FavouriteEvent.GetFavourites)

        observeLiveData()
    }

    private fun observeLiveData() {

        viewModel.favouritesLiveData.observe(viewLifecycleOwner) {
            it?.let {
                changeEmptyStatusVisibility(false)
                favouritesAdapter.update(it)
            }
        }

        viewModel.emptyLiveData.observe(viewLifecycleOwner){
            changeEmptyStatusVisibility(true)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let { mainViewModel.showError(it) }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            it?.let { mainViewModel.showLoading(it) }
        }
    }


    private fun changeEmptyStatusVisibility(visibility: Boolean) {
        binding.apply {
            if (visibility) {
                clEmptyStatus.visibility = View.VISIBLE
                rvFavouritesList.visibility = View.GONE
            } else {
                clEmptyStatus.visibility = View.GONE
                rvFavouritesList.visibility = View.VISIBLE

            }
        }
    }

    override fun onClickFavouriteRecipe(recipeId: Long) {
        findNavController().navigate(
            FavouriteFragmentDirections.actionNavFavouriteFragmentToNavRecipeInfoFragment(
                recipeId = recipeId
            )
        )
    }

}