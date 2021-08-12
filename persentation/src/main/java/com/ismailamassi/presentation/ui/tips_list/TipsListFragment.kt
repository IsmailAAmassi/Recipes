package com.ismailamassi.presentation.ui.tips_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentAddEditRecipeBinding
import com.ismailamassi.presentation.databinding.FragmentTipsListBinding

class TipsListFragment : BaseFragment<FragmentTipsListBinding>(),
    View.OnClickListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTipsListBinding
        get() = FragmentTipsListBinding::inflate

    override fun setup() {

    }

    override fun onClick(v: View?) {

    }

}