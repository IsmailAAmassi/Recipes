package com.ismailamassi.presentation.ui.sign_in

import android.os.Bundle
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
class SignInFragment : BaseFragment() {

    val viewModel: SignInViewModel by viewModels()

    private var signInBinding: FragmentSignInBinding? = null

    override fun setup() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as MainActivity).hideStatusBar()
        signInBinding = FragmentSignInBinding.inflate(inflater, container, false)
        return signInBinding?.apply {

        }?.root
    }


    companion object {
        private const val TAG = "SignInFragment"
    }
}