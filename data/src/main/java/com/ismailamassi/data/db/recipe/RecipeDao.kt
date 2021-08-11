package com.ismailamassi.data.db.recipe

import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames

@Dao
interface RecipeDao {

    /**
     * functions for single
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipeData: RecipeData): Long

    @Update
    suspend fun update(recipeData: RecipeData): Int

    @Query("DELETE FROM ${DatabaseTablesNames.RECIPE_TABLE} WHERE id =:id")
    suspend fun delete(id: Long): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.RECIPE_TABLE} WHERE id =:id")
    suspend fun get(id: Long): RecipeData?

    /**
     * functions for list
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipesData: List<RecipeData>): List<Long>

    @Update
    suspend fun update(recipesData: List<RecipeData>): Int

    @Query("DELETE FROM ${DatabaseTablesNames.RECIPE_TABLE} WHERE id IN (:recipesIds)")
    suspend fun delete(recipesIds: List<Long>): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.RECIPE_TABLE} WHERE id IN (:recipesIds)")
    suspend fun get(recipesIds: List<Long>): List<RecipeData>

    /**
     * functions for table
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.RECIPE_TABLE}")
    suspend fun get(): List<RecipeData>

    @Query("DELETE FROM ${DatabaseTablesNames.RECIPE_TABLE}")
    suspend fun drop(): Int

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.RECIPE_TABLE}")
    suspend fun count(): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.RECIPE_TABLE} WHERE title LIKE :query")
    suspend fun search(query: String): List<RecipeData>

    /**
     * custom queries
     */

    @Query("SELECT R.* FROM ${DatabaseTablesNames.RECIPE_TABLE} R INNER JOIN ${DatabaseTablesNames.FAVOURITE_TABLE} F ON F.recipe_id = R.id")
    suspend fun getFavouritesRecipes(): List<RecipeData>


    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.RECIPE_TABLE} WHERE category_id =:categoryId")
    suspend fun countForCategory(categoryId: Long): Int


//    suspend fun getFullRecipe(id: Long)
}