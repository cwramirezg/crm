package com.github.cwramirezg.crm.home.presentation.home

sealed interface HomeEvent {
    object fetchUserRole : HomeEvent
}