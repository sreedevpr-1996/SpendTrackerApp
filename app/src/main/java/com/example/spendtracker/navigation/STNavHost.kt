package com.example.spendtracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spendtracker.sealedclasses.Screens
import com.example.spendtracker.viewmodels.STMainViewModel
import com.example.spendtracker.views.composables.screens.CreateUserScreen
import com.example.spendtracker.views.composables.screens.HomeScreen
import com.example.spendtracker.views.composables.screens.WelcomeScreen

@Composable
fun STNavHost(navHostController: NavHostController, viewModel: STMainViewModel) {
    NavHost(
        navHostController,
        startDestination = Screens.WelcomeScreen.route
    ) {
        composable(Screens.WelcomeScreen.route) {
            WelcomeScreen(viewModel, navHostController)
        }
        composable(Screens.CreateUserScreen.route) {
            CreateUserScreen(navHostController)
        }
        composable(Screens.HomeScreen.route) {
            HomeScreen(navHostController,viewModel)
        }
    }
}