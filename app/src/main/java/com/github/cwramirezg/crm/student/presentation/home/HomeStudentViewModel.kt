package com.github.cwramirezg.crm.student.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.cwramirezg.crm.core.di.IoDispatcher
import com.github.cwramirezg.crm.student.domain.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeStudentViewModel @Inject constructor(
    val repository: StudentRepository,
    savedStateHandle: SavedStateHandle,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeStudentState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeStudentEvent) {
        when (event) {
            HomeStudentEvent.getCourses -> {

            }
        }
    }
}