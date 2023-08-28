package com.dashingqi.dqtimemanager.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dashingqi.dqtimemanager.config.AppRoute
import com.dashingqi.dqtimemanager.ui.theme.DQTimeManagerTheme
import kotlinx.coroutines.delay

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * @desc : 启动页面
 * @author : zhangqi
 * @time : 2023/8/27 15:43
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashPageView(navigationController: NavController? = null) {
    var textManagerAnimationVisible by remember {
        mutableStateOf(false)
    }

    var clockAnimationVisible by remember {
        mutableStateOf(false)
    }

    var showLineVisible by remember {
        mutableStateOf(false)
    }
    val animatable = remember {
        Animatable(0f)
    }

    var lineAlpha by remember {
        mutableStateOf(0f)
    }

    val canvasRotationValue by animateFloatAsState(
        targetValue = if (lineAlpha == 1f) 360f else 0f, animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(key1 = Unit) {
        withContext(Dispatchers.Main) {
            delay(500)
            clockAnimationVisible = true
            delay(500)
            showLineVisible = true
            // 配置画线的动画
            animatable.animateTo(targetValue = 1.0f, animationSpec = tween(durationMillis = 800, easing = LinearEasing))
        }

        withContext(Dispatchers.Main) {
            delay(1500)
            textManagerAnimationVisible = true
        }
        delay(1000)
        navigationController?.navigate(AppRoute.MAIN_SCREEN)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = clockAnimationVisible, enter = scaleIn(animationSpec = tween(durationMillis = 500))
        ) {
            Canvas(
                modifier = Modifier
                    .size(100.dp)
                    .rotate(canvasRotationValue)
            ) {
                val centerOffset = Offset(size.width / 2, size.height / 2)
                // 画圆圈
                drawCircle(
                    color = Color.Yellow,
                    radius = size.minDimension / 2,
                    center = Offset(size.width / 2, size.height / 2)
                )
                if (showLineVisible) {
                    // 画直线
                    lineAlpha = animatable.value
                    drawLine(
                        color = Color.Red,
                        start = centerOffset,
                        end = Offset(size.width / 2, size.height / 2 + size.height / 3),
                        strokeWidth = 4f,
                        cap = StrokeCap.Round,
                        alpha = lineAlpha
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        AnimatedVisibility(
            visible = textManagerAnimationVisible,
            enter = expandVertically(expandFrom = Alignment.Bottom) + fadeIn(initialAlpha = 0.1f)
        ) {
            Text(text = "时间管理器", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

    }

}

@Preview(showBackground = true)
@Composable
fun SplashViewPreview() {
    DQTimeManagerTheme {
        SplashPageView()
    }
}