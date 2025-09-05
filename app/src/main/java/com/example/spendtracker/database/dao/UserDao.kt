package com.example.spendtracker.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.spendtracker.database.tables.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM users ORDER BY timestamp DESC LIMIT 1")
    fun getLatestUser(): Flow<UserEntity?>

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}