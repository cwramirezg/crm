package com.github.cwramirezg.crm.authentication.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOutlineTextField(
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Opciones"
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
        ) {
            OutlinedTextField(
                value = selectedOption.ifEmpty { "Seleccione" },
                onValueChange = { },
                label = { Text(label) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Mostrar opciones"
                    )
                },
              //  isError = selectedOption,
                readOnly = true
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(0.dp)
                    .clickable(onClick = { showDialog = true })
            )
        }

        if (showDialog) {
            Dialog(
                onDismissRequest = { showDialog = false },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Selecciona una opción") },
                                navigationIcon = {
                                    IconButton(onClick = { showDialog = false }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Cerrar"
                                        )
                                    }
                                }
                            )
                        }
                    ) { innerPadding ->
                        LazyColumn(
                            modifier = Modifier
                                .padding(innerPadding)
                                .fillMaxSize()
                        ) {
                            items(options) { option ->
                                Column {
                                    Text(
                                        text = option,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                selectedOption = option
                                                showDialog = false
                                                onOptionSelected(option)
                                            }
                                            .padding(16.dp),
                                        fontSize = 16.sp
                                    )
                                    Divider()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListOutlineTextFieldPreview() {
    ListOutlineTextField(
        options = listOf("Opción 1", "Opción 2", "Opción 3"),
        onOptionSelected = {},
    )
}