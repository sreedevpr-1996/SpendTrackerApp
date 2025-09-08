package com.example.spendtracker.data.models.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.spendtracker.constants.TableConstants
import com.example.spendtracker.models.database.tables.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(userEntity: UserEntity)

    @Transaction
    @Query("SELECT * FROM ${TableConstants.USERS} ORDER BY timestamp DESC LIMIT 1")
    fun getLatestUser(): Flow<UserEntity?>

    @Transaction
    @Query("DELETE FROM ${TableConstants.USERS}")
    suspend fun deleteAllUsers()
}