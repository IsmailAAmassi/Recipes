package com.ismailamassi.presentation.ui.recipes_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.recipe.RecipeDto
import com.ismailamassi.presentation.databinding.ItemRecipeListBinding
import timber.log.Timber

class RecipesListAdapter(
    private val recipeListListener: RecipeListListener
) : RecyclerView.Adapter<RecipesListAdapter.RecipesListViewHolder>() {

    private var recipesList = listOf<RecipeDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(data: List<RecipeDto>) {
        this.recipesList = data
        notifyDataSetChanged()
    }

    inner class RecipesListViewHolder(val view: ItemRecipeListBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(recipeDto: RecipeDto) {
            view.recipe = recipeDto
            view.recipeListListener = recipeListListener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeListBinding.inflate(inflater, parent, false)
        return RecipesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesListViewHolder, position: Int) {
        val currentRecipe = recipesList[position]
        Timber.tag("TAG").d("onBindViewHolder : currentRecipe $currentRecipe")
        holder.bindView(currentRecipe)
    }

    override fun getItemCount(): Int = recipesList.size
}