package com.example.spendtracker.data.models.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.spendtracker.common.constants.CommonConstants.ZERO
import com.example.spendtracker.constants.TableConstants
import com.example.spendtracker.models.database.tables.UserEntity

@Entity(
    tableName = TableConstants.TRANSACTIONS,
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = [TableConstants.ID],
            childColumns = [TableConstants.USER_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = [TableConstants.ID],
            childColumns = [TableConstants.CATEGORY_ID],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index(TableConstants.USER_ID), Index(TableConstants.CATEGORY_ID)]
)
data class TransactionHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = ZERO,
    @ColumnInfo(name = TableConstants.TRANSACTION_TYPE) val transactionType: String,
    @ColumnInfo(name = TableConstants.AMOUNT) val amount: Double,
    @ColumnInfo(name = TableConstants.TIMESTAMP) val timestamp: Long,
    @ColumnInfo(name = TableConstants.CATEGORY_ID) val category: Int,
    @ColumnInfo(name = TableConstants.USER_ID) val userId: Int,
)
