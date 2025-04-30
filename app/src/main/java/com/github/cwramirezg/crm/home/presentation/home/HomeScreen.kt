package com.github.cwramirezg.crm.home.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.crm.ui.theme.CRMTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    CRMTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            Home(
                state = state,
                onEvento = { viewModel.onEvent(it) }
            )
        }
    }
}

@Composable
fun Home(
    state: HomeState,
    onEvento: (HomeEvent) -> Unit,
) {
    LaunchedEffect(null) {
        onEvento(HomeEvent.fetchUserRole)
    }
    when (state.rol) {
        "" -> {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        "Profesor" -> {
            Text("profesor")
        }

        "Estudiante" -> {
            Text("estudiante")
        }

        else -> {
            Text("Rol desconocido")
        }
    }
}