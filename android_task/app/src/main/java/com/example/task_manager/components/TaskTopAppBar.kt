package com.example.task_manager.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonShapes

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.task_manager.R

@RequiresApi(Build.VERSION_CODES.M)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTopAppBar() {
    TopAppBar(
        title = { Text("Task Manager") },
        actions = {
            IconButton(onClick = { /* Navigate to Settings */ }) {
               // Icon(Icon(painterResource(R.drawable.)), contentDescription = "Settings")
            }
        }
    )
}
