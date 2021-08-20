package com.ismailamassi.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.databinding.ItemHomeRecipeBinding
import com.ismailamassi.presentation.ui.home.listeners.HomeRecipesListener

class HomeRecipesAdapter constructor(
    private val homeRecipesListener: HomeRecipesListener
) : RecyclerView.Adapter<HomeRecipesAdapter.HomeRecipesViewHolder>() {

    private var recipesList = listOf<RecipeDto>()

    fun update(data: List<RecipeDto>) {
        this.recipesList = data
        notifyDataSetChanged()
    }

    inner class HomeRecipesViewHolder(val view: ItemHomeRecipeBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(recipeDto: RecipeDto) {
            view.recipe = recipeDto
            view.homeRecipesListener = homeRecipesListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecipesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeRecipeBinding.inflate(inflater, parent, false)
        return HomeRecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeRecipesViewHolder, position: Int) {
        val currentRecipe = recipesList[position]
        holder.bindView(recipeDto = currentRecipe)
    }

    override fun getItemCount(): Int = recipesList.size
}