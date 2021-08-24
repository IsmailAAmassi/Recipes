package com.ismailamassi.presentation.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentFavouriteBinding

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteBinding
        get() = FragmentFavouriteBinding::inflate

    override fun setup() {
        (requireActivity() as MainActivity).configAppBar()
        (requireActivity() as MainActivity).showBottomNavigationView()
    }

}