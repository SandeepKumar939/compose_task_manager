package com.example.task_manager.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_manager.di.DispatchersProvider
import com.example.task_manager.repository.TaskRepository
import com.example.task_manager.room.Priority
import com.example.task_manager.room.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val dispatchersProvider: DispatchersProvider
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks


    fun loadTasks() {
        viewModelScope.launch(dispatchersProvider.io) {
            repository.getAllTasks().collect { _tasks.value = it }
        }
    }


    fun addTask(
        title: String,
        description: String?,
        priority: Priority,
        dueDate: Date,
        isCompleted: Boolean = false
    ) {
        val newTask = Task(
            title = title,
            description = description,
            priority = priority,
            dueDate = dueDate,
            isCompleted = isCompleted
        )
        viewModelScope.launch {
            repository.insertTask(newTask)
        }
    }


    fun completeTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task.copy(isCompleted = true)) // Update in DB
            loadTasks() // Reload tasks to reflect the update
        }
    }


    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            loadTasks()
        }
    }

    fun getTaskById(taskId: Int): Flow<Task?> {
        return repository.getTaskById(taskId)
    }


    /**
     * not yet implemented
     */
    fun reorderTasks(fromIndex: Int, toIndex: Int) {
        _tasks.value = _tasks.value.toMutableList().apply {
            add(toIndex, removeAt(fromIndex))
        }
    }


}




