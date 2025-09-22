package com.example.spendtracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.spendtracker.data.models.TransactionWithUserAndCategory
import com.example.spendtracker.data.repositories.STTransactionRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class STTransactionsViewModel @Inject constructor(
    private val transactionRepo: STTransactionRepo,
) : ViewModel() {

    private val _transactions =
        MutableStateFlow<PagingData<TransactionWithUserAndCategory>>(PagingData.empty())
    val transactions: StateFlow<PagingData<TransactionWithUserAndCategory>> = _transactions
    fun getTransactionByUserId(userId: Int) {
        viewModelScope.launch {
            transactionRepo.getAllTransactionsForUser(userId).cachedIn(viewModelScope).collect {
                _transactions.value = it
            }
        }
    }


}