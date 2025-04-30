package com.github.cwramirezg.crm.home.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.github.cwramirezg.crm.core.IoDispatcher
import com.github.cwramirezg.crm.home.domain.home.usecase.HomeUseCases
import com.github.cwramirezg.crm.navigation.Home
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val home = savedStateHandle.toRoute<Home>()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.fetchUserRole -> {
                viewModelScope.launch(dispatcher) {
                    homeUseCases.getRolUseCase(home.uid) { success, rol ->
                        if (success) {
                            _state.value = state.value.copy(
                                rol = rol
                            )
                        }
                    }
                }
            }
        }
    }

}