package com.example.spendtracker.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spendtracker.models.database.dao.TransactionsDao
import com.example.spendtracker.models.database.dao.UserDao
import com.example.spendtracker.models.database.tables.CategoryEntity
import com.example.spendtracker.models.database.tables.TransactionHistoryEntity
import com.example.spendtracker.models.database.tables.UserEntity

@Database(
    entities = [UserEntity::class, TransactionHistoryEntity::class, CategoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class STDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionsDao(): TransactionsDao


}