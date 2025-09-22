package com.example.spendtracker.models.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendtracker.common.constants.CommonConstants.ZERO
import com.example.spendtracker.constants.TableConstants

@Entity(tableName = TableConstants.USERS)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = ZERO,
    @ColumnInfo(name = TableConstants.FIRST_NAME) val firstName: String,
    @ColumnInfo(name = TableConstants.LAST_NAME) val lastName: String,
    @ColumnInfo(name = TableConstants.EMAIL) val email: String,
    @ColumnInfo(name = TableConstants.BALANCE) val balance: Double,
    @ColumnInfo(name = TableConstants.TIMESTAMP) val timestamp: Long,
    @ColumnInfo(name = TableConstants.CURRENCY) val currency: String = "$",
)