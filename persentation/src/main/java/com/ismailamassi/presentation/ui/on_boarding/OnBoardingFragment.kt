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
class OnBoardingFragment : BaseFragment() {

    private val viewModel: OnBoardingViewModel by viewModels()

    private var boardingBinding: FragmentOnBoardingBinding? = null

    override fun setup() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).hideStatusBar()
        boardingBinding = FragmentOnBoardingBinding.inflate(inflater,container,false)
        return boardingBinding?.apply {

        }?.root
    }


    private fun onClickSkipOnBoarding() {
        viewModel.onTriggerEvent(OnBoardingEvent.SkipOnBoarding)
        throw NullPointerException("Test Error")
    }

}