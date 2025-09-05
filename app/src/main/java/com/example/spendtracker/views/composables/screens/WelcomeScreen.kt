package com.example.spendtracker.views.composables.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.spendtracker.constants.SizeConstants
import com.example.spendtracker.sealedclasses.Screens
import com.example.spendtracker.viewmodels.STMainViewModel
import com.example.spendtracker.views.composables.helpers.CreateUser
import com.example.spendtracker.views.composables.helpers.WelcomeUser

@Composable
fun WelcomeScreen(viewModel: STMainViewModel, navController: NavHostController) {
    val user = viewModel.userEntity.collectAsState()
    val fontSize = SizeConstants.largeFont
    val arrowModifier =
        Modifier
            .padding(SizeConstants.clickablePadding)
            .size(SizeConstants.largeIconSize)
    Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            WelcomeUser(user.value, {
                navController.navigate(Screens.HomeScreen.route) {
                    launchSingleTop = true
                }
            }, fontSize, arrowModifier)



            CreateUser({
                navController.navigate(Screens.CreateUserScreen.route)
            }, fontSize, arrowModifier)
        }
    }
}