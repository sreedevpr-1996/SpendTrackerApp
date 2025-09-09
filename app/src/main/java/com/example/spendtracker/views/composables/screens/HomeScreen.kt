package com.example.spendtracker.views.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.spendtracker.R
import com.example.spendtracker.common.constants.CommonConstants.ZERO
import com.example.spendtracker.constants.SizeConstants
import com.example.spendtracker.viewmodels.STMainViewModel
import com.example.spendtracker.viewmodels.STTransactionsViewModel
import com.example.spendtracker.views.composables.composehelpers.AddTransaction
import com.example.spendtracker.views.composables.composehelpers.CustomText
import com.example.spendtracker.views.composables.composehelpers.TransactionCard
import com.example.spendtracker.views.composables.composehelpers.VerticalSpacer


@Composable
fun HomeScreen(navHostController: NavHostController, mainViewModel: STMainViewModel) {
    val user = mainViewModel.userEntity.collectAsState()
    val transactionsViewModel: STTransactionsViewModel = hiltViewModel()
    val transactions = transactionsViewModel
        .transactions.collectAsLazyPagingItems()
    Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
        Column {
            Card(
                modifier = Modifier
                    .padding(
                        top = innerPadding.calculateTopPadding() + SizeConstants.dp08,
                        start = SizeConstants.dp12,
                        end = SizeConstants.dp12,
                        bottom = SizeConstants.dp08
                    )
                    .fillMaxWidth(),
                shape = RoundedCornerShape(SizeConstants.dp25),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF7202FF)
                ),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    CustomText(

                        stringResource(
                            R.string.hello_user,
                            "${user.value?.firstName} ${user.value?.lastName}"
                        ), fontSize = 22.sp
                    )
                    VerticalSpacer(SizeConstants.dp08)
                    Row {
                        CustomText(
                            stringResource(
                                R.string.balance,
                                user.value?.currency.toString(),
                                user.value?.balance.toString()
                            ), fontSize = 25.sp
                        )
                        Icon(
                            Icons.Filled.Create,
                            contentDescription = stringResource(R.string.currency),
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {

                                }
                                .background(Color.Transparent))
                    }
                }

            }
            Column(modifier = Modifier.fillMaxSize()) {
                val modifier = if (transactions.itemCount != ZERO) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier.fillMaxSize()
                }

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(transactions.itemCount) {
                        TransactionCard(transactions[it]?.transaction)
                    }
                    transactions.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { CircularProgressIndicator() }
                            }

                            loadState.append is LoadState.Loading -> {
                                item { CircularProgressIndicator() }
                            }

                            loadState.append is LoadState.Error -> {
                                val e = transactions.loadState.append
                                        as LoadState.Error
                                item { CustomText("Error: ${e.error.localizedMessage}") }
                            }
                        }
                    }
                }
                AddTransaction(modifier, transactionsViewModel)
            }
        }

    }
}