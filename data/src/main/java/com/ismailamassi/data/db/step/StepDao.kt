package com.ismailamassi.data.db.step

import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames
import com.ismailamassi.data.db.ingredient.IngredientData

@Dao
interface StepDao {

    /**
     * functions for single
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stepData: StepData): Long

    @Update
    suspend fun update(stepData: StepData): Int

    @Query("DELETE FROM ${DatabaseTablesNames.STEP_TABLE} WHERE id =:id")
    suspend fun delete(id: Long): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.STEP_TABLE} WHERE id =:id")
    suspend fun get(id: Long): StepData?

    /**
     * functions for list
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stepsData: List<StepData>): List<Long>

    @Update
    suspend fun update(stepsData: List<StepData>): Int

    @Query("DELETE FROM ${DatabaseTablesNames.STEP_TABLE} WHERE id IN (:stepsIds)")
    suspend fun delete(stepsIds: List<Long>): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.STEP_TABLE} WHERE id IN (:stepsIds)")
    suspend fun get(stepsIds: List<Long>): List<StepData>

    /**
     * functions for table
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.STEP_TABLE}")
    suspend fun get(): List<StepData>

    @Query("DELETE FROM ${DatabaseTablesNames.STEP_TABLE}")
    suspend fun drop(): Int

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.STEP_TABLE}")
    suspend fun count(): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.STEP_TABLE} WHERE description LIKE :query")
    suspend fun search(query: String): List<StepData>

    /**
     * custom queries
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.STEP_TABLE} WHERE recipe_id =:recipeId")
    suspend fun getForRecipe(recipeId: Long): List<StepData>

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.STEP_TABLE}")
    suspend fun count2(): Int
}