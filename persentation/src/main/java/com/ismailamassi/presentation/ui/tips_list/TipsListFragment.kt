package com.ismailamassi.presentation.ui.tips_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ismailamassi.presentation.base.BaseFragment
import com.ismailamassi.presentation.databinding.FragmentTipsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TipsListFragment : BaseFragment<FragmentTipsListBinding>(),
    View.OnClickListener {

    private val viewModel: TipViewModel by viewModels()

    val tipListAdapter: TipListAdapter by lazy {
        TipListAdapter()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTipsListBinding
        get() = FragmentTipsListBinding::inflate

    override fun setup() {
        binding.apply {
            tipListAdapter = this@TipsListFragment.tipListAdapter
        }

        viewModel.onTriggerEvent(TipEvent.GetTips)
//        tipListAdapter.update(fakeTips)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.tipLiveData.observe(viewLifecycleOwner) {
            it?.let {
                tipListAdapter.update(it)
                viewModel.tipLiveData.postValue(null)
            }
        }
    }

    override fun onClick(v: View?) {

    }

}