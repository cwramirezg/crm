package com.github.cwramirezg.crm.teacher.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.crm.core.data.model.Course
import com.github.cwramirezg.crm.ui.theme.CRMTheme

@Composable
fun HomeTeacherScreen(
    viewModel: HomeTeacherViewModel = hiltViewModel(),
    onNavigateToCourse: (String) -> Unit,
    onNavigateToCreateCourse: (String) -> Unit,
) {
    CRMTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            HomeTeacher(
                state = state,
                onEvent = { viewModel.onEvent(it) },
                onNavigateToCourse = { onNavigateToCourse(it) },
                onNavigateToCreateCourse = { onNavigateToCreateCourse(it) }
            )
        }
    }
}

@Composable
fun HomeTeacher(
    state: HomeTeacherState,
    onEvent: (HomeTeacherEvent) -> Unit,
    onNavigateToCourse: (String) -> Unit,
    onNavigateToCreateCourse: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        onEvent(HomeTeacherEvent.getCourses)
    }
    Scaffold(
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    onNavigateToCreateCourse(state.uidUser)
                }
            ) {
                Icon(Icons.Filled.Add, "Crear curso")
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            Text(
                text = "Tus cursos",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            LazyColumn {
                items(state.courses) { course ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                onNavigateToCourse(course.id)
                            }
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(course.name, fontWeight = FontWeight.Bold)
                            Text(course.description)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTeacherPreview() {
    HomeTeacher(
        state = HomeTeacherState(
            courses = listOf(
                Course("1", "Curso 1", "Descripción 1", "1", emptyList()),
                Course("2", "Curso 2", "Descripción 2", "1", emptyList()),
            )
        ),
        onEvent = {},
        onNavigateToCourse = {},
        onNavigateToCreateCourse = {}
    )
}
