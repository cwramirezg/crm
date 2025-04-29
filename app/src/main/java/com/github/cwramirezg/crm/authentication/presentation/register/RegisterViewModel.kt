package com.github.cwramirezg.crm.authentication.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.crm.authentication.domain.register.usecase.RegisterUseCases
import com.github.cwramirezg.crm.core.IoDispatcher
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCases: RegisterUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.submit -> {
                viewModelScope.launch(dispatcher) {
                    registerUseCases.getRegisterUseCase(
                        name = state.value.name,
                        email = state.value.email,
                        password = state.value.password,
                        role = state.value.role,
                    ) { success, errorMessage ->
                        if (success) {
                            val user = FirebaseAuth.getInstance().currentUser
                            Timber.d("Result: $user")
                        }
                    }
                }
            }

            is RegisterEvent.updateName -> {
                _state.value = state.value.copy(
                    name = event.name,
                    error = ""
                )
            }

            is RegisterEvent.updateEmail -> {
                _state.value = state.value.copy(
                    email = event.email,
                    error = ""
                )
            }

            is RegisterEvent.updatePassword -> {
                _state.value = state.value.copy(
                    password = event.password,
                    error = ""
                )
            }

            is RegisterEvent.updateConfirmPassword -> {
                _state.value = state.value.copy(
                    confirmPassword = event.confirmPassword,
                    error = ""
                )
            }

            is RegisterEvent.updateRole -> {
                _state.value = state.value.copy(
                    role = event.role,
                    error = ""
                )
            }
        }
    }
}