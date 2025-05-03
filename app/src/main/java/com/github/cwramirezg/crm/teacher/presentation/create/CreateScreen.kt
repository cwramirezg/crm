package com.github.cwramirezg.crm.teacher.presentation.create

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.cwramirezg.crm.authentication.presentation.composable.NameOutlineTextField
import com.github.cwramirezg.crm.ui.theme.CRMTheme

@Composable
fun CreateScreen(
    viewModel: CreateViewModel = hiltViewModel()
) {
    CRMTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val state by viewModel.state.collectAsState()
            Create(
                state = state,
                onEvent = { viewModel.onEvent(it) }
            )
        }
    }
}

@Composable
fun Create(
    state: CreateState,
    onEvent: (CreateEvent) -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            Text(
                text = "Curso",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            NameOutlineTextField(
                value = state.name,
                onValueChange = { onEvent(CreateEvent.updateName(it)) },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                label = "Nombre"
            )
            NameOutlineTextField(
                value = state.description,
                onValueChange = { onEvent(CreateEvent.updateDescription(it)) },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                label = "Descripción"
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = state.name.isNotEmpty() && state.description.isNotEmpty(),
                onClick = {
                    onEvent(CreateEvent.createCourse)
                }
            ) {
                Text("Crear")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursePreview() {
    Create(
        state = CreateState(),
        onEvent = {}
    )
}
