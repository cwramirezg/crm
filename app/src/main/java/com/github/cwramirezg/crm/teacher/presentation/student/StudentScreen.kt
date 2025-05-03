package com.github.cwramirezg.crm.teacher.presentation.student

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.crm.ui.theme.CRMTheme

@Composable
fun StudentScreen(
    viewModel: StudentViewModel = hiltViewModel(),
) {
    CRMTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            Student(
                state = state,
                onEvent = { viewModel.onEvent(it) }
            )
        }
    }
}

@Composable
fun Student(
    state: StudentState,
    onEvent: (StudentEvent) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(StudentEvent.getStudents)
    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Selecciona un estudiante")
            LazyColumn {
                items(state.students) { student ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                onEvent(StudentEvent.addStudentToCourse(student.uid))
                            }
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(student.name)
                            Text(student.email)
                        }
                    }
                }
            }
        }
    }
}
