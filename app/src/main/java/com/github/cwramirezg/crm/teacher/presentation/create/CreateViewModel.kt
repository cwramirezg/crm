package com.github.cwramirezg.crm.teacher.presentation.create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.cwramirezg.crm.core.di.IoDispatcher
import com.github.cwramirezg.crm.teacher.domain.usecases.TeacherUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val teacherUseCases: TeacherUseCases,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(CreateState())
    val state = _state.asStateFlow()


    fun onEvent(event: CreateEvent) {
        when (event) {
            CreateEvent.createCourse -> {
                viewModelScope.launch(dispatcher) {
                }
            }

            is CreateEvent.updateDescription -> {
                _state.value = _state.value.copy(
                    description = event.description
                )
            }

            is CreateEvent.updateName -> {
                _state.value = _state.value.copy(
                    name = event.name
                )
            }
        }
    }

}
