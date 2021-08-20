package com.ismailamassi.presentation.ui.home

import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.MainViewModel
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentHomeBinding
import com.ismailamassi.presentation.ui.home.adapters.HomeCategoriesAdapter
import com.ismailamassi.presentation.ui.home.adapters.HomeRecipesAdapter
import com.ismailamassi.presentation.ui.home.listeners.HomeCategoriesListener
import com.ismailamassi.presentation.ui.home.listeners.HomeRecipesListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    View.OnClickListener, HomeCategoriesListener, HomeRecipesListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: HomeViewModel by viewModels()

    private val homeCategoriesAdapter: HomeCategoriesAdapter by lazy {
        HomeCategoriesAdapter(this)
    }

    private val homeRecipeAdapter: HomeRecipesAdapter by lazy {
        HomeRecipesAdapter(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Timber.tag(TAG).d("setup : handleOnBackPressed")
//                showAreYouSureDialog()
                handleDoubleClick()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun setup() {
        (requireActivity() as MainActivity).configAppBar()
        (requireActivity() as MainActivity).showBottomNavigationView()

        setHasOptionsMenu(true)

        binding.apply {
            onClickListener = this@HomeFragment
            homeCategoriesAdapter = this@HomeFragment.homeCategoriesAdapter
            homeRecipeAdapter = this@HomeFragment.homeRecipeAdapter
        }

        homeCategoriesAdapter.update(fakeCategories)
        homeRecipeAdapter.update(fakeRecipes)
    }

    var doubleBackToExitPressedOnce = false
    private fun handleDoubleClick() {
        if (doubleBackToExitPressedOnce) {
            requireActivity().finish()
        }
        Toast.makeText(requireContext(), "Press again to exit", Toast.LENGTH_SHORT).show()
        doubleBackToExitPressedOnce = true
        lifecycleScope.launch {
            delay(2000)
            doubleBackToExitPressedOnce = false
        }
    }


    fun showAreYouSureDialog() {
        val builder = AlertDialog.Builder(requireContext()).apply {
            setTitle("Exit?")
            setMessage("Are you sure you want to exit App?")
            setPositiveButton("Stay") { dialog, which ->
                dialog.dismiss()
            }

            setNegativeButton("Exit") { _, which ->
                requireActivity().finish()
            }
        }
        builder.create().show()
    }

    override fun onClick(v: View?) {
        binding.run {
            when (v) {
                tvHomeSearch -> onClickSearch()
                cvHomeBestCollection -> onBestCollection()
                cvHomeMostLovedRecipes -> onClickMostLoved()
                tvHomeMostViewedRecipes -> onClickMostViewed()
            }
        }
    }

    private fun onClickMostViewed() {
        findNavController().navigate(
            HomeFragmentDirections.actionNavHomeFragmentToNavRecipesListFragment(
                recipesType = RecipeListType.MostViewedRecipes
            )
        )
    }

    private fun onClickMostLoved() {
        findNavController().navigate(
            HomeFragmentDirections.actionNavHomeFragmentToNavRecipesListFragment(
                recipesType = RecipeListType.MostLovedRecipes
            )
        )
    }

    private fun onBestCollection() {
        findNavController().navigate(
            HomeFragmentDirections.actionNavHomeFragmentToNavRecipesListFragment(
                recipesType = RecipeListType.BestCollectionRecipes
            )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(com.ismailamassi.presentation.R.menu.home_menu, menu)
    }

    private fun onClickSearch() {
        findNavController().navigate(HomeFragmentDirections.actionGlobalNavSearchFragment())
    }


    override fun onClickCategory(categoryId: Long) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavHomeFragmentToNavRecipesListFragment(
                recipesType = RecipeListType.CategoryRecipes(categoryId)
            )
        )
    }

    override fun onClickRecipe(recipeId: Long) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavHomeFragmentToNavRecipeInfoFragment(
                recipeId = recipeId
            )
        )
    }
}