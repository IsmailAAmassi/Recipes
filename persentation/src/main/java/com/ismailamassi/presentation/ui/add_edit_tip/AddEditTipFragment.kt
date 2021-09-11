package com.ismailamassi.presentation.ui.add_edit_tip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentAddEditRecipeBinding
import com.ismailamassi.presentation.databinding.FragmentAddEditTipBinding


class AddEditTipFragment : BaseFragment<FragmentAddEditTipBinding>(),
    View.OnClickListener {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddEditTipBinding
        get() = FragmentAddEditTipBinding::inflate

    override fun setup() {
    }

    override fun onClick(v: View?) {
    }

}