package com.ismailamassi.data.db.favourite

import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames
import com.ismailamassi.data.db.category.CategoryData

@Dao
interface FavouriteDao {

    /**
     * functions for single
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favouriteData: FavouriteData): Long

    @Update
    suspend fun update(favouriteData: FavouriteData)

    @Query("DELETE FROM ${DatabaseTablesNames.FAVOURITE_TABLE} WHERE id =:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM ${DatabaseTablesNames.FAVOURITE_TABLE} WHERE id =:id")
    suspend fun get(id: Long): FavouriteData

    /**
     * functions for list
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoritesData: List<FavouriteData>)

    @Update
    suspend fun update(favoritesData: List<FavouriteData>)

    @Query("DELETE FROM ${DatabaseTablesNames.FAVOURITE_TABLE} WHERE id IN (:favoritesIds)")
    suspend fun delete(favoritesIds: List<String>)

    @Query("SELECT * FROM ${DatabaseTablesNames.FAVOURITE_TABLE} WHERE id IN (:favoritesIds)")
    suspend fun get(favoritesIds: List<String>): List<FavouriteData>

    /**
     * functions for table
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.FAVOURITE_TABLE}")
    suspend fun get(): List<FavouriteData>

    @Query("DELETE FROM ${DatabaseTablesNames.FAVOURITE_TABLE}")
    suspend fun drop()

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.FAVOURITE_TABLE}")
    suspend fun count(): Int


    /**
     * custom queries
     */


    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.FAVOURITE_TABLE}")
    suspend fun count2(): Int
}