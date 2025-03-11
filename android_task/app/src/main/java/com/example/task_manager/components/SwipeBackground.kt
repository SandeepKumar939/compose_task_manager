package com.example.task_manager.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeBackground(dismissState: DismissState) {
    val color by animateColorAsState(
        if (dismissState.targetValue == DismissValue.DismissedToStart) Color.Red
        else if (dismissState.targetValue == DismissValue.DismissedToEnd) Color.Green
        else Color.Gray
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = if (dismissState.targetValue == DismissValue.DismissedToStart) Icons.Default.Delete
            else Icons.Default.Check,
            contentDescription = "Swipe action",
            tint = Color.White
        )
    }
}
