package com.example.task_manager.components


import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    // Create the shimmer animation
    val shimmerAnimation = infiniteTransition.animateFloat(
        initialValue = -1.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            Color.LightGray.copy(alpha = 0.3f),
            Color.Gray.copy(alpha = 0.5f),
            Color.LightGray.copy(alpha = 0.3f)
        ),
        start = androidx.compose.ui.geometry.Offset(shimmerAnimation.value * 1000f, 0f),
        end = androidx.compose.ui.geometry.Offset((shimmerAnimation.value + 1f) * 1000f, 0f)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(brush = brush, shape = RectangleShape)
    )
}

@Composable
@Preview
fun ShimmerEffectPreview() {
    ShimmerEffect(modifier = Modifier.padding(16.dp))
}


