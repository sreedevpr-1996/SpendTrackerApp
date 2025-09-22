package com.example.spendtracker.data.models.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendtracker.common.constants.CommonConstants.ZERO
import com.example.spendtracker.constants.TableConstants

@Entity(tableName = TableConstants.CATEGORIES)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = ZERO,
    @ColumnInfo(name = TableConstants.NAME) val name: String,
)
