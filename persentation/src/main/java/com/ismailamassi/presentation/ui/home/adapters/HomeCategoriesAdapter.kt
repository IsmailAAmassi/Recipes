package com.ismailamassi.presentation.ui.home.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.presentation.databinding.ItemHomeCategoryBinding
import com.ismailamassi.presentation.ui.home.listeners.HomeCategoriesListener

class HomeCategoriesAdapter(
    private val homeCategoriesListener: HomeCategoriesListener
) : RecyclerView.Adapter<HomeCategoriesAdapter.HomeCategoriesViewHolder>() {

    private var categoriesList = listOf<CategoryDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<CategoryDto>) {
        categoriesList = data
        notifyDataSetChanged()
    }


    inner class HomeCategoriesViewHolder(val view: ItemHomeCategoryBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(categoryDto: CategoryDto) {
            view.category = categoryDto
            view.categoriesHomeListener = homeCategoriesListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeCategoryBinding.inflate(inflater, parent, false)
        return HomeCategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeCategoriesViewHolder, position: Int) {
        val currentCategory = categoriesList[position]
        holder.bindView(currentCategory)
    }

    override fun getItemCount(): Int = categoriesList.size
}