package com.ismailamassi.data.db.tip

import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames

@Dao
interface TipDao {

    /**
     * functions for single
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tipData: TipData): Long

    @Update
    suspend fun update(tipData: TipData)

    @Query("DELETE FROM ${DatabaseTablesNames.TIP_TABLE} WHERE id =:id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM ${DatabaseTablesNames.TIP_TABLE} WHERE id =:id")
    suspend fun get(id: Long): TipData

    /**
     * functions for list
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tipsData: List<TipData>)

    @Update
    suspend fun update(tipsData: List<TipData>)

    @Query("DELETE FROM ${DatabaseTablesNames.TIP_TABLE} WHERE id IN (:tipsIds)")
    suspend fun delete(tipsIds: List<String>)

    @Query("SELECT * FROM ${DatabaseTablesNames.TIP_TABLE} WHERE id IN (:tipsIds)")
    suspend fun get(tipsIds: List<String>): List<TipData>

    /**
     * functions for table
     */

    @Query("SELECT * FROM ${DatabaseTablesNames.TIP_TABLE}")
    suspend fun get(): List<TipData>

    @Query("DELETE FROM ${DatabaseTablesNames.TIP_TABLE}")
    suspend fun drop()

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.TIP_TABLE}")
    suspend fun count(): Int

    @Query("SELECT * FROM ${DatabaseTablesNames.TIP_TABLE} WHERE label LIKE :query")
    suspend fun search(query: String): List<TipData>

    /**
     * custom queries
     */
    @Query("SELECT * FROM ${DatabaseTablesNames.TIP_TABLE} WHERE id IN (SELECT id FROM ${DatabaseTablesNames.TIP_TABLE} ORDER BY RANDOM() LIMIT 1)")
    suspend fun getRandom(): TipData
}