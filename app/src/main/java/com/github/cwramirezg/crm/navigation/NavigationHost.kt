package com.github.cwramirezg.crm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.cwramirezg.crm.authentication.presentation.login.LoginScreen
import com.github.cwramirezg.crm.authentication.presentation.register.RegisterScreen
import com.github.cwramirezg.crm.home.presentation.home.HomeScreen

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
                onLoginSuccess = { uid ->
                    navHostController.navigate(Home(uid))
                },
                onNavigateToRegister = { navHostController.navigate(Register) },
            )
        }
        composable<Register> {
            RegisterScreen(
                onLoginSuccess = { uid ->
                    navHostController.navigate(Home(uid))
                },
            )
        }
        composable<Home> {
            HomeScreen()
        }
    }
}
