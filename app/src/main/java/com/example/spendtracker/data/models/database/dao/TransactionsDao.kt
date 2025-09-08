package com.example.spendtracker.models.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.spendtracker.constants.TableConstants
import com.example.spendtracker.models.TransactionWithUserAndCategory
import com.example.spendtracker.models.database.tables.TransactionHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDao {
    @Insert
    suspend fun insertTransaction(transactionHistoryEntity: TransactionHistoryEntity)

    @Transaction
    @Query("SELECT * FROM ${TableConstants.TRANSACTIONS} WHERE ${TableConstants.ID} = :transactionId")
    fun getTransactionById(transactionId: Int): Flow<TransactionWithUserAndCategory?>


    @Transaction
    @Query("SELECT * FROM ${TableConstants.TRANSACTIONS} " +
            "WHERE ${TableConstants.USER_ID} = :userId ORDER BY timestamp DESC")
    fun getAllTransactionsForUser(userId: Int): PagingSource<Int, TransactionWithUserAndCategory>

    @Transaction
    @Query("DELETE FROM ${TableConstants.TRANSACTIONS}")
    suspend fun deleteAllTransactions()
}