package com.ismailamassi.presentation.ui.categories_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentCategoriesListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


@AndroidEntryPoint
class CategoriesListFragment : BaseFragment<FragmentCategoriesListBinding>(), View.OnClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCategoriesListBinding
        get() = FragmentCategoriesListBinding::inflate

    private val viewModel: CategoriesListViewModel by viewModels()

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

    override fun setup() {
        (requireActivity() as MainActivity).showStatusBar()
        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).configAppBar()

        binding.button.setOnClickListener {
            findNavController().navigate(CategoriesListFragmentDirections.actionNavHomeFragmentToNavRecipesListFragment())
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

    }

}