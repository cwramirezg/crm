package com.github.cwramirezg.crm.authentication.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropdownMenu(
    selectedRole: String,
    onRoleSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val roles = listOf("student", "teacher")

    Box {
        Text(
            text = selectedRole,
            modifier = Modifier
                .clickable { expanded.value = true }
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(8.dp)
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            roles.forEach { role ->
                DropdownMenuItem(
                    onClick = {
                        onRoleSelected(role)
                        expanded.value = false
                    },
                    text = { Text(role.capitalize()) }
                )
            }
        }
    }
}
