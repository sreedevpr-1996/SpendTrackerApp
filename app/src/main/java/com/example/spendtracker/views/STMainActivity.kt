package com.example.spendtracker.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.spendtracker.navigation.STNavHost
import com.example.spendtracker.ui.theme.SpendTrackerTheme
import com.example.spendtracker.viewmodels.STMainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class STMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: STMainViewModel = hiltViewModel()
            SpendTrackerTheme {
                val navController = rememberNavController()
                STNavHost(navController, viewModel)
            }
        }
    }
}


