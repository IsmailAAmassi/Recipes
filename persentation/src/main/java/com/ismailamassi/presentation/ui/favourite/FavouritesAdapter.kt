package com.ismailamassi.presentation.ui.favourite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.databinding.ItemFavouriteBinding

class FavouritesAdapter(
    private val favouritesListener: FavouritesListener
) : RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {

    var recipes = listOf<RecipeDto>()

    fun update(data:List<RecipeDto>){
        recipes = data
        notifyDataSetChanged()
    }

    inner class FavouritesViewHolder(val view: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(recipeDto: RecipeDto) {
            view.apply {
                favouritesListener = this@FavouritesAdapter.favouritesListener
                recipe = recipeDto
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavouriteBinding.inflate(inflater, parent, false)
        return FavouritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bindView(currentRecipe)
    }

    override fun getItemCount(): Int = recipes.size
}