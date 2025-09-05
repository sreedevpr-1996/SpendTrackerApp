package com.example.spendtracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spendtracker.database.dao.UserDao
import com.example.spendtracker.database.tables.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class STDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}