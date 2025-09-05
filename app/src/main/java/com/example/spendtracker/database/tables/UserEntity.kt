package com.example.spendtracker.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "balance") val balance: Double,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "currency") val currency: String = "$",
)