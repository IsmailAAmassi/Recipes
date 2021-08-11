package com.ismailamassi.data.db.ingredient

import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames

@Dao
interface IngredientDao {

    /**
     * functions for single
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredientData: IngredientData): Long

    @Update
    suspend fun update(ingredientData: IngredientData): Int

    @Query("DELETE FROM ${DatabaseTablesNames.INGREDIENT_TABLE} WHERE id =:id")
    suspend fun delete(id: Long): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.INGREDIENT_TABLE} WHERE id =:id")
    suspend fun get(id: Long): IngredientData?

    /**
     * functions for list
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ingredientsData: List<IngredientData>): List<Long>

    @Update
    suspend fun update(ingredientsData: List<IngredientData>): Int

    @Query("DELETE FROM ${DatabaseTablesNames.INGREDIENT_TABLE} WHERE id IN (:ingredientsIds)")
    suspend fun delete(ingredientsIds: List<Long>): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.INGREDIENT_TABLE} WHERE id IN (:ingredientsIds)")
    suspend fun get(ingredientsIds: List<Long>): List<IngredientData>

    /**
     * functions for table
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.INGREDIENT_TABLE}")
    suspend fun get(): List<IngredientData>

    @Query("DELETE FROM ${DatabaseTablesNames.INGREDIENT_TABLE}")
    suspend fun drop(): Int

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.INGREDIENT_TABLE}")
    suspend fun count(): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.INGREDIENT_TABLE} WHERE name LIKE :query")
    suspend fun search(query: String): List<IngredientData>

    /**
     * custom queries
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.INGREDIENT_TABLE} WHERE recipe_id =:recipeId")
    suspend fun getForRecipe(recipeId: Long): List<IngredientData>

}