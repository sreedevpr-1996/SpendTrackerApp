package com.example.spendtracker.repositories


import com.example.spendtracker.database.dao.UserDao
import com.example.spendtracker.database.tables.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class STUserRepo @Inject constructor(private val userDao: UserDao) {
    suspend fun insertUser(userEntity: UserEntity) = userDao.insertUser(userEntity)
    fun getLatestUser(): Flow<UserEntity?> = userDao.getLatestUser()
    suspend fun deleteAllUsers() = userDao.deleteAllUsers()

}