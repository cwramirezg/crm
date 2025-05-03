package com.github.cwramirezg.crm.teacher.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.github.cwramirezg.crm.core.di.IoDispatcher
import com.github.cwramirezg.crm.navigation.HomeTeacher
import com.github.cwramirezg.crm.teacher.domain.usecases.TeacherUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeTeacherViewModel @Inject constructor(
    val usecase: TeacherUseCases,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeTeacherState())
    val state = _state.asStateFlow()

    private val homeTeacher = savedStateHandle.toRoute<HomeTeacher>()

    fun onEvent(event: HomeTeacherEvent) {
        when (event) {
            HomeTeacherEvent.getCourses -> {
                viewModelScope.launch(dispatcher) {
                    usecase.getCourses(
                        homeTeacher.uid
                    ) {
                        _state.value = state.value.copy(
                            courses = it
                        )
                    }
                }
            }
        }
    }

}
