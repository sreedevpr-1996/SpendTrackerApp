package com.example.spendtracker.sealedclasses

import com.example.spendtracker.constants.TransactionType

sealed class TransactionChoices(val transactionType: String) {
    object Income : TransactionChoices(TransactionType.INCOME)
    object Expense : TransactionChoices(TransactionType.EXPENSE)
}