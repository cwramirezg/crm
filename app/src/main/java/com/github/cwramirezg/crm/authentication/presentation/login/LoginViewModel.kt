package com.github.cwramirezg.crm.authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.crm.authentication.domain.login.usecase.LoginUseCases
import com.github.cwramirezg.crm.core.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.onLogin -> {
                viewModelScope.launch(dispatcher) {
                    val result = loginUseCases.getLoginUseCase(
                        state.value.email,
                        state.value.password
                    )
                    result.getOrNull()?.also { user ->
                        _state.value = state.value.copy(
                            uid = user.uid,
                            isLoggedIn = true,
                        )
                        Timber.d("Result: ${user.uid}")
                    }
                }
            }

            is LoginEvent.updatePassword -> {
                _state.value = state.value.copy(
                    password = event.password,
                    error = ""
                )
            }

            is LoginEvent.updateEmail -> {
                _state.value = state.value.copy(
                    email = event.email,
                    error = ""
                )
            }
        }
    }
}