package com.example.task_manager.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float // progress should be a value between 0 and 1 (0% to 100%)
) {
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000)
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        // Circle background (gray color)
        Canvas(
            modifier = Modifier.size(50.dp)
        ) {
            drawArc(
                color = Color.LightGray,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = true,
                size = size
            )
        }

        // Circular progress arc
        Canvas(
            modifier = Modifier.size(50.dp)
        ) {
            drawArc(
                color = Color.DarkGray,
                startAngle = -90f,
                sweepAngle = 360f * animatedProgress.value,
                useCenter = true,
                size = size
            )
        }

        // Display percentage text in the center
        Text(
            text = "${(animatedProgress.value * 100).toInt()}%",
            color = Color.White
        )
    }
}
