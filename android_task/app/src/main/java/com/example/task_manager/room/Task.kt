package com.example.task_manager.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String?,
    val priority: Priority,
    val dueDate: Date,  // Change to java.util.Date
    val isCompleted: Boolean = false
)


enum class Priority { LOW, MEDIUM, HIGH }
