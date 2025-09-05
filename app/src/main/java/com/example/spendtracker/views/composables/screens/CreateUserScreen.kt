package com.example.spendtracker.views.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.spendtracker.R
import com.example.spendtracker.sealedclasses.Screens
import com.example.spendtracker.viewmodels.STCreateUserViewModel
import com.example.spendtracker.views.composables.helpers.VerticalSpacer
import com.example.spendtracker.views.composables.helpers.CustomButton
import com.example.spendtracker.views.composables.helpers.EditInfo
import com.example.spendtracker.views.composables.helpers.SetSnackBar

@Composable
fun CreateUserScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<STCreateUserViewModel>()

    val emptyString: String = stringResource(R.string.empty)
    val createUserFirstName = rememberSaveable { mutableStateOf(emptyString) }
    val createUserLastName = rememberSaveable { mutableStateOf(emptyString) }
    val createUserBalance = rememberSaveable { mutableStateOf(emptyString) }
    val createUserEmail = rememberSaveable { mutableStateOf(emptyString) }
    val successMessage = stringResource(R.string.user_created)
    val emailValidationFailed = stringResource(R.string.email_is_invalid)
    val balanceValidationFailed: String = stringResource(R.string.balance_is_invalid)
    val firstNameValidationFailed = stringResource(R.string.first_name_is_invalid)
    val lastNameValidationFailed = stringResource(R.string.last_name_is_invalid)
    viewModel.setErrorMessages(
        emailValidationFailed,
        firstNameValidationFailed,
        lastNameValidationFailed,
        balanceValidationFailed
    )
    val snackBarHostState = remember { SnackbarHostState() }
    var disableButton by remember { mutableStateOf(false) }

    SetSnackBar(
        viewModel.snackBarMessage,
        snackBarHostState,
        { disableButton = false },
        { disableButton = true })
    Scaffold(
        modifier = Modifier.Companion.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start

        ) {
            EditInfo(
                stringResource(R.string.first_name),
                createUserFirstName.value,
                { it -> createUserFirstName.value = it })
            EditInfo(
                stringResource(R.string.last_name),
                createUserLastName.value,
                { it -> createUserLastName.value = it })
            EditInfo(
                stringResource(R.string.email),
                createUserEmail.value,
                { it -> createUserEmail.value = it })
            EditInfo(
                stringResource(R.string.current_balance),
                createUserBalance.value,
                { it -> createUserBalance.value = it },
                true
            )
            VerticalSpacer()
            CustomButton(stringResource(R.string.submit), submit = {
                viewModel.createUser(
                    viewModel.createUserEntity(
                        createUserFirstName.value,
                        createUserLastName.value,
                        createUserEmail.value,
                        createUserBalance.value
                    )
                ) {
                    viewModel.showSnackBar(successMessage)
                    navController.navigate(Screens.HomeScreen.route) {
                        popUpTo(Screens.CreateUserScreen.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            }, isDisabled = disableButton)

        }
    }
}