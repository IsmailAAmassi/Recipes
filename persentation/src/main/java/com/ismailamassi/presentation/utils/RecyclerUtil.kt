package com.ismailamassi.presentation.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

object RecyclerUtil {
    @JvmStatic
    @BindingAdapter(value = ["app:setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
        this.run { this.adapter = adapter }
    }

    @JvmStatic
    @BindingAdapter(value = ["app:spans"])
    fun RecyclerView.bindRecyclerViewSpanCount(spanCount: Int) {
        this.run {
            this.adapter = adapter
//            layoutManager = GridLayoutManager(context, spanCount)
            (layoutManager as GridLayoutManager).spanCount = spanCount
        }
    }

}