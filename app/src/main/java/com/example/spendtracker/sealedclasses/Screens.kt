package com.example.spendtracker.sealedclasses

import com.example.spendtracker.constants.ScreenRoutes

sealed class Screens(val route: String) {
    object WelcomeScreen : Screens(ScreenRoutes.WELCOME_SCREEN_ROUTE)
    object CreateUserScreen : Screens(ScreenRoutes.CREATE_USER_SCREEN_ROUTE)
    object HomeScreen : Screens(ScreenRoutes.HOME_SCREEN_ROUTE)
}