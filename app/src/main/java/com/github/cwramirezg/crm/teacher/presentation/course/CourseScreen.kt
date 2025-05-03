package com.github.cwramirezg.crm.teacher.presentation.course

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.crm.ui.theme.CRMTheme

@Composable
fun CourseScreen(
    viewModel: CourseViewModel = hiltViewModel(),
    onNavigateToSelectStudent: (String) -> Unit,
) {
    CRMTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            Course(
                state = state,
                onEvent = { viewModel.onEvent(it) },
                onNavigateToSelectStudent = { onNavigateToSelectStudent(it) }
            )
        }
    }
}

@Composable
fun Course(
    state: CourseState,
    onEvent: (CourseEvent) -> Unit,
    onNavigateToSelectStudent: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(CourseEvent.getStudentsInCourse)
    }
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Estudiantes del curso")
            LazyColumn {
                items(state.students) { student ->
                    Text("- ${student.name} (${student.email})", Modifier.padding(8.dp))
                }
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { onNavigateToSelectStudent(state.courseId) }
            ) {
                Text("Agregar estudiante")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursePreview() {
    Course(
        state = CourseState(),
        onEvent = {},
        onNavigateToSelectStudent = {}
    )
}