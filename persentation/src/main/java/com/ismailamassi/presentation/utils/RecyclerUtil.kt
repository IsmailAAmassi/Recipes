package com.ismailamassi.presentation.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerUtil {
    @JvmStatic
    @BindingAdapter(value = ["app:setAdapter"])
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>?) {
        this.run { this.adapter = adapter }
    }

}