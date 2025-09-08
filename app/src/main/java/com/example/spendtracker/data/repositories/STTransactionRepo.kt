package com.example.spendtracker.repositories


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.spendtracker.models.TransactionWithUserAndCategory
import com.example.spendtracker.models.database.dao.TransactionsDao
import com.example.spendtracker.models.database.tables.TransactionHistoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class STTransactionRepo @Inject constructor(private val transactionsDao: TransactionsDao) {
    suspend fun insertTransaction(transactionHistoryEntity: TransactionHistoryEntity) =
        transactionsDao.insertTransaction(transactionHistoryEntity)

    fun getTransactionById(transactionId: Int): Flow<TransactionWithUserAndCategory?> =
        transactionsDao.getTransactionById(transactionId)

    suspend fun deleteAllTransactions() = transactionsDao.deleteAllTransactions()

    fun getAllTransactionsForUser(userId:Int): Flow<PagingData<TransactionWithUserAndCategory>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { transactionsDao.getAllTransactionsForUser(userId) }
        ).flow
    }

}