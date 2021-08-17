package com.ismailamassi.presentation.ui.recipe_info.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismailamassi.domain.model.recipe.IngredientDto
import com.ismailamassi.domain.model.recipe.StepDto
import com.ismailamassi.presentation.databinding.ItemRecipeInfoIngredientBinding
import com.ismailamassi.presentation.databinding.ItemRecipeInfoStepBinding

class RecipeInfoStepAdapter :
    RecyclerView.Adapter<RecipeInfoStepAdapter.RecipeInfoStepAdapterViewHolder>() {

    private var ingredientsList = listOf<StepDto>()

    fun update(data: List<StepDto>) {
        ingredientsList = data
        notifyDataSetChanged()
    }

    inner class RecipeInfoStepAdapterViewHolder(val view: ItemRecipeInfoStepBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindView(stepDto: StepDto) {
            view.step = stepDto
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeInfoStepAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RecipeInfoStepAdapterViewHolder(ItemRecipeInfoStepBinding.inflate(inflater,parent,false))
    }

    override fun onBindViewHolder(
        holder: RecipeInfoStepAdapterViewHolder,
        position: Int
    ) {
        val currentIngredient = ingredientsList[position]
        holder.bindView(currentIngredient)
    }

    override fun getItemCount(): Int = ingredientsList.size
}