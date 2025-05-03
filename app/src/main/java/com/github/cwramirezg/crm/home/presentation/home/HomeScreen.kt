package com.github.cwramirezg.crm.home.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.crm.ui.theme.CRMTheme
import timber.log.Timber

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToTeacher: (String) -> Unit,
    onNavigateToStudent: (String) -> Unit,
) {
    CRMTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            Home(
                state = state,
                onEvent = { viewModel.onEvent(it) },
                onNavigateToTeacher = { onNavigateToTeacher(it) },
                onNavigateToStudent = { onNavigateToStudent(it) }
            )
        }
    }
}

@Composable
fun Home(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onNavigateToTeacher: (String) -> Unit,
    onNavigateToStudent: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(HomeEvent.fetchUserRole)
    }
    LaunchedEffect(state.rol) {
        when (state.rol) {
            "teacher" -> {
                Timber.d("teacher: ${state.uid}")
                onNavigateToTeacher(state.uid)
            }

            "student" -> {
                Timber.d("student: ${state.uid}")
                onNavigateToStudent(state.uid)
            }
        }
    }
    Scaffold {
        Box(
            Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}