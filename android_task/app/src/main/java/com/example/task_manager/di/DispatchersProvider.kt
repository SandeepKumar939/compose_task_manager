package com.example.task_manager.di

import kotlinx.coroutines.CoroutineDispatcher

data class DispatchersProvider(
    val main: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val default: CoroutineDispatcher
)
