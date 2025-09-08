package com.example.spendtracker.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.spendtracker.constants.TableConstants
import com.example.spendtracker.models.database.tables.CategoryEntity
import com.example.spendtracker.models.database.tables.TransactionHistoryEntity
import com.example.spendtracker.models.database.tables.UserEntity

data class TransactionWithUserAndCategory(
    @Embedded val transaction: TransactionHistoryEntity,
    @Relation(
        parentColumn = TableConstants.CATEGORY_ID,
        entityColumn = TableConstants.ID
    ) val category: CategoryEntity?,
    @Relation(
        parentColumn = TableConstants.USER_ID,
        entityColumn = TableConstants.ID
    ) val user: UserEntity,
)