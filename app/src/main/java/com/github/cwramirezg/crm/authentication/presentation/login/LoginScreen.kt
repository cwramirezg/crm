package com.github.cwramirezg.crm.authentication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.crm.R
import com.github.cwramirezg.crm.authentication.presentation.composable.EmailOutlineTextField
import com.github.cwramirezg.crm.ui.theme.CRMTheme
import com.github.cwramirezg.themovie.authentication.presentation.composable.PasswordOutlineTextField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (String) -> Unit,
    onNavigateToRegister: () -> Unit
) {
    CRMTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            Login(
                state = state,
                onEvent = { viewModel.onEvent(it) },
                onLoginSuccess = { onLoginSuccess(it) },
                onNavigateToRegister = { onNavigateToRegister() }
            )
        }
    }
}

@Composable
fun Login(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onLoginSuccess: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
) {
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onLoginSuccess(state.uid)
        }
    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Logo"
            )
            Text(
                text = "Inicia sesión",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            EmailOutlineTextField(
                value = state.email,
                onValueChange = { onEvent(LoginEvent.updateEmail(it)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            PasswordOutlineTextField(
                text = "Contraseña",
                value = state.password,
                onValueChange = { onEvent(LoginEvent.updatePassword(it)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            TextButton(
                onClick = { onNavigateToRegister() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("¿No tienes cuenta? Regístrate")
            }
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = state.email.isNotEmpty() && state.password.isNotEmpty(),
                onClick = {
                    onEvent(LoginEvent.onLogin)
                }
            ) {
                Text("Ingresar")
            }
            if (state.error.isNotEmpty()) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = state.error,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login(
        state = LoginState(),
        onEvent = {},
        onLoginSuccess = {},
        onNavigateToRegister = {}
    )
}