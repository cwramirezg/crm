package com.github.cwramirezg.crm.teacher.presentation.create

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.github.cwramirezg.crm.core.di.IoDispatcher
import com.github.cwramirezg.crm.navigation.CreateCourse
import com.github.cwramirezg.crm.teacher.domain.usecases.TeacherUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCourseViewModel @Inject constructor(
    private val usecase: TeacherUseCases,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(CreateCourseState())
    val state = _state.asStateFlow()

    private val createCourse = savedStateHandle.toRoute<CreateCourse>()

    init {
        _state.value = state.value.copy(
            uidUser = createCourse.uidUser
        )
    }

    fun onEvent(event: CreateCourseEvent) {
        when (event) {
            CreateCourseEvent.createCourse -> {
                viewModelScope.launch(dispatcher) {
                    usecase.createCourse(
                        state.value.name,
                        state.value.description,
                        state.value.uidUser
                    ) { success, message ->
                        if (success) {
                            _state.value = state.value.copy(
                                isSuccess = true
                            )
                        } else {
                            _state.value = state.value.copy(
                                message = message
                            )
                        }
                    }
                }
            }

            is CreateCourseEvent.updateDescription -> {
                _state.value = _state.value.copy(
                    description = event.description
                )
            }

            is CreateCourseEvent.updateName -> {
                _state.value = _state.value.copy(
                    name = event.name
                )
            }
        }
    }

}
