package com.example.spendtracker.views.composables.composehelpers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.spendtracker.R
import com.example.spendtracker.common.constants.CommonConstants.ZERO
import com.example.spendtracker.constants.SizeConstants
import com.example.spendtracker.sealedclasses.TransactionChoices
import com.example.spendtracker.viewmodels.STTransactionsViewModel

@Composable
fun AddTransaction(modifier: Modifier, transactionsViewModel: STTransactionsViewModel) {
    var showDialog by rememberSaveable { mutableStateOf(false) }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (showDialog)
            AddTransactionDialog({ showDialog = false },transactionsViewModel)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Filled.AddCircle,
                contentDescription = stringResource(R.string.currency),
                modifier = Modifier
                    .clickable {
                        showDialog = true
                    }
                    .padding(SizeConstants.clickablePadding)
            )
            CustomText(stringResource(R.string.add_transaction), fontSize = SizeConstants.sp23)
        }
    }
}

@Composable
fun AddTransactionDialog(dismissDialog: () -> Unit, transactionsViewModel: STTransactionsViewModel) {
    val emptyString = stringResource(R.string.empty)
    var choice by rememberSaveable { mutableStateOf(emptyString) }
    val incomeChoice = TransactionChoices.Income.transactionType
    val expenseChoice = TransactionChoices.Expense.transactionType
    var amount by rememberSaveable { mutableStateOf(emptyString) }
    var selectedDateTime by rememberSaveable { mutableStateOf(emptyString) }
    var category by rememberSaveable { mutableIntStateOf(ZERO) }


    Dialog(onDismissRequest = { dismissDialog() }) {
        Box(
            modifier =
                Modifier
                    .clip(RoundedCornerShape(SizeConstants.dp25))

                    .background(MaterialTheme.colorScheme.background)

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                EditInfo(
                    stringResource(R.string.amount), amount,
                    {
                        amount = it
                    }, true
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    RadioButton(
                        onClick = { choice = incomeChoice },
                        selected = choice == incomeChoice
                    )
                    CustomText(stringResource(R.string.income))
                    HorizontalSpacer(SizeConstants.dp25)
                    RadioButton(
                        onClick = { choice = expenseChoice },
                        selected = choice == expenseChoice
                    )
                    CustomText(stringResource(R.string.expense))

                }
                CustomDateTimeField(
                    stringResource(R.string.select_date_and_time),
                    selectedDateTime = selectedDateTime.toLongOrNull(),
                    onDateTimeSelected = { selectedDateTime = it.toString() })

                CustomButton(
                    stringResource(R.string.submit),
                    {
//                        transactionsViewModel.addTransaction(amount,choice,selectedDateTime,category)
                    }, isDisabled = false
                )
            }
        }
    }
}