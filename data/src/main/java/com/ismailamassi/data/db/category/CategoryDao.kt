package com.ismailamassi.data.db.category

import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames

@Dao
interface CategoryDao {

    /**
     * functions for single
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryData: CategoryData): Long

    @Update
    suspend fun update(categoryData: CategoryData): Int

    @Query("DELETE FROM ${DatabaseTablesNames.CATEGORY_TABLE} WHERE id =:id")
    suspend fun delete(id: Long): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.CATEGORY_TABLE} WHERE id =:id")
    suspend fun get(id: Long): CategoryData?

    /**
     * functions for list
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoriesData: List<CategoryData>): List<Long>

    @Update
    suspend fun update(categoriesData: List<CategoryData>): Int

    @Query("DELETE FROM ${DatabaseTablesNames.CATEGORY_TABLE} WHERE id IN (:categoriesIds)")
    suspend fun delete(categoriesIds: List<Long>): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.CATEGORY_TABLE} WHERE id IN (:categoriesIds)")
    suspend fun get(categoriesIds: List<Long>): List<CategoryData>

    /**
     * functions for table
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.CATEGORY_TABLE}")
    suspend fun get(): List<CategoryData>

    @Query("DELETE FROM ${DatabaseTablesNames.CATEGORY_TABLE}")
    suspend fun drop(): Int

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.CATEGORY_TABLE}")
    suspend fun count(): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.CATEGORY_TABLE} WHERE title LIKE :query")
    suspend fun search(query: String): List<CategoryData>

    /**
     * custom queries
     */

}