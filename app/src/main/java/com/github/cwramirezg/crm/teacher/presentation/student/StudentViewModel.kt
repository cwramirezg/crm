package com.github.cwramirezg.crm.teacher.presentation.student

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
class StudentViewModel @Inject constructor(
    val usecase: TeacherUseCases,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(StudentState())
    val state = _state.asStateFlow()

    fun onEvent(event: StudentEvent) {
        when (event) {
            is StudentEvent.addStudentToCourse -> {

            }

            StudentEvent.getStudents -> {
                viewModelScope.launch(dispatcher) {
                    usecase.getAllStudents {
                        _state.value = state.value.copy(
                            students = it
                        )
                    }
                }
            }
        }
    }
}