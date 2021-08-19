package com.ismailamassi.presentation.ui.more

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentMoreBinding

class MoreFragment : BaseFragment<FragmentMoreBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoreBinding
        get() = FragmentMoreBinding::inflate

    override fun setup() {

    }

}