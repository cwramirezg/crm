package com.github.cwramirezg.crm.student.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.crm.ui.theme.CRMTheme

@Composable
fun HomeStudentScreen(
    viewModel: HomeStudentViewModel = hiltViewModel()
) {
    CRMTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            Home(
                state = state,
                onEvent = { viewModel.onEvent(it) }
            )
        }
    }
}

@Composable
fun Home(
    state: HomeStudentState,
    onEvent: (HomeStudentEvent) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(HomeStudentEvent.getCourses)
    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            Text(
                text = "Cursos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            LazyColumn {
                items(state.courses) { course ->
                    Text("${course.name}: ${course.description}")
                }
            }
        }
    }
}
