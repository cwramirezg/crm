package com.github.cwramirezg.crm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.cwramirezg.crm.authentication.presentation.login.LoginScreen
import com.github.cwramirezg.crm.authentication.presentation.register.RegisterScreen

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: Any
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable<Login> {
            LoginScreen(
                onLoginSuccess = {},
                onNavigateToRegister = { navHostController.navigate(Register) },
            )
        }
        composable<Register> {
            RegisterScreen(
                onLoginSuccess = {},
            )
        }
    }
}
