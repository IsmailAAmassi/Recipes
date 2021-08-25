package com.ismailamassi.presentation.ui.login

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ismailamassi.presentation.MainActivity
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentSignInBinding>(),View.OnClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignInBinding
        get() = FragmentSignInBinding::inflate

    val viewModel: LoginViewModel by viewModels()

    private var signInBinding: FragmentSignInBinding? = null

    override fun setup() {
        (requireActivity() as MainActivity).supportActionBar?.hide()
    }


    override fun onClick(v: View?) {
    }
}