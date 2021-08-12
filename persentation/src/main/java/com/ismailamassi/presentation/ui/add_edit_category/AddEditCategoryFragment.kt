package com.ismailamassi.presentation.ui.add_edit_category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ismailamassi.presentation.R
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentAboutBinding
import com.ismailamassi.presentation.databinding.FragmentAddEditCategoryBinding

class AddEditCategoryFragment : BaseFragment<FragmentAddEditCategoryBinding>(),View.OnClickListener  {


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddEditCategoryBinding
        get() = FragmentAddEditCategoryBinding::inflate

    override fun setup() {
    }

    override fun onClick(v: View?) {
    }

}