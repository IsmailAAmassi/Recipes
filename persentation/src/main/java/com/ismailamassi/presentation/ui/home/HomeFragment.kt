package com.ismailamassi.presentation.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.ismailamassi.presentation.ui.home.listeners.CategoriesHomeListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.random.Random

class HomeFragment : BaseFragment<FragmentHomeBinding>(),
    View.OnClickListener, CategoriesHomeListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: HomeViewModel by viewModels()

    private val homeCategoriesAdapter: HomeCategoriesAdapter by lazy {
        HomeCategoriesAdapter(this)
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
        (requireActivity() as MainActivity).showStatusBar()
        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).configAppBar()

        binding.apply {
            onClickListener = this@HomeFragment
            homeCategoriesAdapter = this@HomeFragment.homeCategoriesAdapter
            todayTip = fakeTips[Random.nextInt(fakeTips.size.minus(1))]
        }

        homeCategoriesAdapter.update(fakeCategories.subList(0, fakeCategories.size.div(2)))
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
                tvSeeAllCategories,
                tvHomeExploreByCategory -> onClickSeeAllCategories()
            }
        }
    }

    private fun onClickSearch() {
        findNavController().navigate(HomeFragmentDirections.actionGlobalNavSearchFragment())
    }

    private fun onClickSeeAllCategories() {
        findNavController().navigate(HomeFragmentDirections.actionNavHomeFragmentToNavCategoriesListFragment())

    }

    override fun onClickCategory(categoryId: Long) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavHomeFragmentToNavRecipesListFragment(
                categoryId = categoryId
            )
        )
    }
}