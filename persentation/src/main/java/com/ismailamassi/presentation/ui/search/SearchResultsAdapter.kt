package com.ismailamassi.presentation.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.databinding.ItemSearchResultBinding

class SearchResultsAdapter constructor(
    private val searchResultListener: SearchResultListener
) : RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {

    private var recipesList = listOf<RecipeDto>()

    fun update(data: List<RecipeDto>) {
        recipesList = data
        notifyDataSetChanged()
    }

    inner class SearchResultsViewHolder(val view: ItemSearchResultBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(recipeDto: RecipeDto) {
            view.recipe = recipeDto
            view.searchResultListener = searchResultListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchResultBinding.inflate(inflater, parent, false)
        return SearchResultsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {
        val currentRecipe = recipesList[position]
        holder.bindView(currentRecipe)
    }

    override fun getItemCount(): Int = recipesList.size
}