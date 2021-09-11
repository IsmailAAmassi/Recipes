package com.ismailamassi.presentation.ui.tips_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.tip.TipDto
import com.ismailamassi.presentation.databinding.ItemTipListBinding
import timber.log.Timber

class TipListAdapter : RecyclerView.Adapter<TipListAdapter.TipListViewHolder>() {

    var tipList = listOf<TipDto>()

    fun update(data: List<TipDto>) {
        Timber.tag("TipListAdapter").d("update : data ${data.size}")
        tipList = data
        notifyDataSetChanged()
    }

    class TipListViewHolder(val view: ItemTipListBinding) : RecyclerView.ViewHolder(view.root) {
        fun bindView(tipDto: TipDto) {
            view.tip = tipDto
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTipListBinding.inflate(inflater, parent, false)
        return TipListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TipListViewHolder, position: Int) {
        val currentTip = tipList[position]
        holder.bindView(currentTip)
    }

    override fun getItemCount(): Int = tipList.size
}