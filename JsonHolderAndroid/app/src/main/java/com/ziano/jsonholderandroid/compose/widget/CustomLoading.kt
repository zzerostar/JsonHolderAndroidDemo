package com.ziano.jsonholderandroid.compose.widget

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/25
 * @desc
 */


@Composable
fun CustomFullScreenLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomLoading(Modifier.align(Alignment.Center), size = 50.dp)
    }
}
@Composable
fun CustomLoading(modifier: Modifier, size: Dp = 30.dp) {

    val sizeFloat = with(LocalDensity.current) { size.roundToPx().toFloat() }

    val width = remember {
        mutableStateOf(sizeFloat)
    }

    val height = remember {
        mutableStateOf(sizeFloat)
    }

    val centerX = width.value / 2
    val centerY = height.value / 2

    val radius = centerX.coerceAtLeast(centerY) - 5f

    val transition = rememberInfiniteTransition()

    val angleDiff = transition.animateFloat(
        initialValue = 0f, targetValue = 360f, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
    )

    val circleRadiusF = sizeFloat / 30

    val circleSize = transition.animateFloat(
        initialValue = circleRadiusF, targetValue = circleRadiusF + 15, animationSpec = InfiniteRepeatableSpec(
            tween(durationMillis = 1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = modifier) {
        drawArc(
            color = Color.Blue,
            startAngle = 0f + angleDiff.value,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(centerX - radius, centerY - radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(2f, cap = StrokeCap.Round)
        )

        drawArc(
            color = Color.Blue,
            startAngle = 180f + angleDiff.value,
            sweepAngle = 90f,
            useCenter = false,
            topLeft = Offset(centerX - radius, centerY - radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(2f, cap = StrokeCap.Round)
        )

        drawCircle(
            color = Color.Blue, center = Offset(centerX, centerY), radius = circleSize.value
        )

    }
}