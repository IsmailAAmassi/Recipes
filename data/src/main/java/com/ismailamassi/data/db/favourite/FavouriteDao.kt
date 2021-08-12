package com.ismailamassi.data.db.favourite

import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames

@Dao
interface FavouriteDao {

    /**
     * functions for single
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favouriteData: FavouriteData): Long

    @Update
    suspend fun update(favouriteData: FavouriteData): Int

    @Query("DELETE FROM ${DatabaseTablesNames.FAVOURITE_TABLE} WHERE recipe_id =:id")
    suspend fun delete(id: Long): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.FAVOURITE_TABLE} WHERE recipe_id =:id")
    suspend fun get(id: Long): FavouriteData?

    /**
     * functions for list
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoritesData: List<FavouriteData>): List<Long>

    @Update
    suspend fun update(favoritesData: List<FavouriteData>): Int

    @Query("DELETE FROM ${DatabaseTablesNames.FAVOURITE_TABLE} WHERE recipe_id IN (:favoritesIds)")
    suspend fun delete(favoritesIds: List<Long>)

    @Query("SELECT * FROM ${DatabaseTablesNames.FAVOURITE_TABLE} WHERE recipe_id IN (:favoritesIds)")
    suspend fun get(favoritesIds: List<Long>): List<FavouriteData>

    /**
     * functions for table
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.FAVOURITE_TABLE}")
    suspend fun get(): List<FavouriteData>

    @Query("DELETE FROM ${DatabaseTablesNames.FAVOURITE_TABLE}")
    suspend fun drop(): Int

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.FAVOURITE_TABLE}")
    suspend fun count(): Int


    /**
     * custom queries
     */

}