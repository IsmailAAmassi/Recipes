package com.ismailamassi.data.db.settings

import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames

@Dao
interface SettingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(settingsData: SettingsData): Long

    @Update
    suspend fun update(settingsData: SettingsData)

    @Delete
    suspend fun delete(settingsData: SettingsData)

    @Query("SELECT * FROM ${DatabaseTablesNames.SETTINGS_TABLE} WHERE id =:id")
    suspend fun get(id: Long): SettingsData

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.SETTINGS_TABLE}")
    suspend fun getCount(): Int
}