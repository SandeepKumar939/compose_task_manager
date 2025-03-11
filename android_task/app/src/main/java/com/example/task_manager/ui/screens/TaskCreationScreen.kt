package com.example.task_manager.ui.screens

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.task_manager.room.Priority
import com.example.task_manager.vm.TaskViewModel
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskCreationScreen(viewModel: TaskViewModel, navController: NavController) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var selectedPriority by remember { mutableStateOf(Priority.LOW) }
    var selectedDate by remember { mutableStateOf(Date()) }

    val calendar = Calendar.getInstance()

    val datePicker = DatePickerDialog(
        navController.context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            selectedDate = calendar.time
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Create Task", style = MaterialTheme.typography.headlineMedium)

        // Title Input (Required)
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title *") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description Input (Optional)
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description (Optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Priority Selection
        Text("Priority", style = MaterialTheme.typography.bodyMedium)
        Row {
            Priority.values().forEach { priority ->
                RadioButton(
                    selected = (selectedPriority == priority),
                    onClick = { selectedPriority = priority }
                )
                Text(priority.name)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Due Date Picker
        Button(onClick = { datePicker.show() }) {
            Text("Select Due Date")
        }
        Text("Selected Date: ${selectedDate.toString()}", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Save Task Button
        Button(
            onClick = {
                if (title.text.isNotBlank()) {
                    viewModel.addTask(
                        title = title.text,
                        description = description.text.takeIf { it.isNotBlank() },
                        priority = selectedPriority,
                        dueDate = selectedDate,
                        isCompleted = false
                    )
                    navController.popBackStack() // Navigate back
                }
            },
            enabled = title.text.isNotBlank()
        ) {
            Text("Save Task")
        }
    }
}
