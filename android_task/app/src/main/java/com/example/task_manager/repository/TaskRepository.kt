package com.example.task_manager.repository

import com.example.task_manager.room.Task
import com.example.task_manager.room.TaskDao
import kotlinx.coroutines.flow.Flow
/**
 * created by sandeep kumar nimmnagoti
 */

class TaskRepository(private val taskDao: TaskDao) {

    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    fun getTasksByStatus(isCompleted: Boolean): Flow<List<Task>> = taskDao.getTasksByStatus(isCompleted)

    suspend fun insertTask(task: Task) = taskDao.insertTask(task)

    suspend fun updateTask(task: Task) = taskDao.updateTask(task)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)

    fun getTaskById(taskId: Int): Flow<Task?> {
        return taskDao.getTaskById(taskId)
    }

}
