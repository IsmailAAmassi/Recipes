package com.ismailamassi.presentation.ui.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>(),View.OnClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnBoardingBinding
        get() = FragmentOnBoardingBinding::inflate

    private val viewModel: OnBoardingViewModel by viewModels()

    private var boardingBinding: FragmentOnBoardingBinding? = null

    override fun setup() {
        (requireActivity() as MainActivity).supportActionBar?.hide()
    }

    private fun onClickSkipOnBoarding() {
        viewModel.onTriggerEvent(OnBoardingEvent.SkipOnBoarding)
    }

    override fun onClick(v: View?) {

    }

}