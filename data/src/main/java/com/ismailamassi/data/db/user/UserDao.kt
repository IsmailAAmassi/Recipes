package com.ismailamassi.data.db.user

import android.provider.ContactsContract
import androidx.room.*
import com.ismailamassi.data.db.DatabaseTablesNames

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userData: UserData): Long

    @Update
    suspend fun update(userData: UserData)

    @Query("DELETE FROM user_table WHERE id =:id")
    suspend fun delete(id: String)

    @Query("SELECT U.* FROM user_table U INNER JOIN settings_table S ON U.id = S.current_user_id LIMIT 1")
    suspend fun getCurrentUser(): UserData?

//    @Query("SELECT U.token FROM user_table U INNER JOIN settings_table S ON U.id = S.current_user_id LIMIT 1")
//    suspend fun getCurrentUserToken(): String?

    @Query("SELECT COUNT(*) FROM ${DatabaseTablesNames.USER_TABLE}")
    suspend fun getCount(): Int
}