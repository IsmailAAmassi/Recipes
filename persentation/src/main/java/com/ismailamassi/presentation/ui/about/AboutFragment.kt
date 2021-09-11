package com.ismailamassi.presentation.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentAboutBinding


class AboutFragment : BaseFragment<FragmentAboutBinding>(),View.OnClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAboutBinding
        get() = FragmentAboutBinding::inflate

    override fun setup() {

    }

    override fun onClick(v: View?) {
    }

}