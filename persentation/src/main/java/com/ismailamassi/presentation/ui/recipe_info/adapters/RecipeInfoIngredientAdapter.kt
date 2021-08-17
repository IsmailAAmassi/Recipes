package com.ismailamassi.presentation.ui.recipe_info.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.presentation.databinding.ItemRecipeInfoIngredientBinding

class RecipeInfoIngredientAdapter :
    RecyclerView.Adapter<RecipeInfoIngredientAdapter.RecipeInfoIngredientViewHolder>() {

    private var ingredientsList = listOf<IngredientDto>()

    fun update(data: List<IngredientDto>) {
        ingredientsList = data
        notifyDataSetChanged()
    }

    inner class RecipeInfoIngredientViewHolder(val view: ItemRecipeInfoIngredientBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(ingredientDto: IngredientDto) {
            view.ingredient = ingredientDto
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeInfoIngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecipeInfoIngredientViewHolder(ItemRecipeInfoIngredientBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(
        holder: RecipeInfoIngredientViewHolder,
        position: Int
    ) {
        val currentIngredient = ingredientsList[position]
        holder.bindView(currentIngredient)
    }

    override fun getItemCount(): Int = ingredientsList.size
}