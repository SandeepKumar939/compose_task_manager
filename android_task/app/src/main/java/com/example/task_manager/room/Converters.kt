package com.example.task_manager.room

import androidx.room.TypeConverter
import java.util.Date
/**
 * created by sandeep kumar nimmnagoti
 */

class Converters {

    @TypeConverter
    fun fromPriority(priority: Priority): String = priority.name

    @TypeConverter
    fun toPriority(value: String): Priority = Priority.valueOf(value)

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toDate(timestamp: Long?): Date? = timestamp?.let { Date(it) }
}
