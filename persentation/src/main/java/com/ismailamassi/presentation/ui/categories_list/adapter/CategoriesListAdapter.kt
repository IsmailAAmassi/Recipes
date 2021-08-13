package com.ismailamassi.presentation.ui.categories_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.category.CategoryDto
import com.ismailamassi.presentation.databinding.ItemCategoryListBinding

class CategoriesListAdapter constructor(
    private val categoriesListListener: CategoriesListListener
) : RecyclerView.Adapter<CategoriesListAdapter.CategoriesListViewHolder>() {

    private var categoriesList = listOf<CategoryDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<CategoryDto>) {
        categoriesList = data
        notifyDataSetChanged()
    }

    inner class CategoriesListViewHolder(val view: ItemCategoryListBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(categoryDto: CategoryDto) {
            view.category = categoryDto
            view.categoriesListListener = categoriesListListener
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryListBinding.inflate(inflater, parent, false)
        return CategoriesListViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoriesListViewHolder,
        position: Int
    ) {
        val currentCategory = categoriesList[position]
        holder.bindView(currentCategory)
    }

    override fun getItemCount(): Int  = categoriesList.size
}