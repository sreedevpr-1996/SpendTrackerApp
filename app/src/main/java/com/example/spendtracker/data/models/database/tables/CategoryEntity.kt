package com.example.spendtracker.models.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spendtracker.constants.TableConstants

@Entity(tableName = TableConstants.CATEGORIES)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = TableConstants.NAME) val name: String,
)
