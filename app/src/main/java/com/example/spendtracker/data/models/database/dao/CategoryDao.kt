package com.example.spendtracker.data.models.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.example.spendtracker.constants.TableConstants
import com.example.spendtracker.data.models.database.tables.CategoryEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = IGNORE)
    suspend fun insertCategory(category: CategoryEntity)

    @Query("SELECT * FROM ${TableConstants.CATEGORIES}")
    suspend fun getAllCategories(): List<CategoryEntity>

}