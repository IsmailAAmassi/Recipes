package com.ismailamassi.data.db.settings

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ismailamassi.data.db.DatabaseTablesNames

@Entity(tableName = DatabaseTablesNames.SETTINGS_TABLE)
data class SettingsData(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "current_user_id") val currentUserId: Long,
    @ColumnInfo(name = "current_user_token") val currentUserToken: String,
    @ColumnInfo(name = "theme") val theme: Int,
    @ColumnInfo(name = "language") val language: String,
    @ColumnInfo(name = "is_first_time") val isFirstTime: Boolean,
    @ColumnInfo(name = "is_login") val isLogin: Boolean,
)