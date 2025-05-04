package com.github.cwramirezg.crm.teacher.presentation.course

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.github.cwramirezg.crm.core.di.IoDispatcher
import com.github.cwramirezg.crm.navigation.Course
import com.github.cwramirezg.crm.teacher.domain.usecases.TeacherUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    val usecase: TeacherUseCases,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(CourseState())
    val state = _state.asStateFlow()

    private val course = savedStateHandle.toRoute<Course>()

    fun onEvent(event: CourseEvent) {
        when (event) {
            CourseEvent.getStudentsInCourse -> {
                viewModelScope.launch(dispatcher) {
                    usecase.getStudentsInCourse(
                        course.uid
                    ) {
                        _state.value = state.value.copy(
                            courseId = course.uid,
                            students = it
                        )
                    }
                }
            }
        }
    }

}
