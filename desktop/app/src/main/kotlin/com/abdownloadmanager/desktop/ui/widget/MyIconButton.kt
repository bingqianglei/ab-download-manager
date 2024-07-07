package com.abdownloadmanager.desktop.ui.widget

import com.abdownloadmanager.desktop.ui.icon.IconSource
import com.abdownloadmanager.desktop.ui.icon.MyIcon
import com.abdownloadmanager.desktop.ui.theme.myColors
import com.abdownloadmanager.desktop.ui.util.ifThen
import com.abdownloadmanager.desktop.utils.div
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun alphaFlicker(): Float {
    val t = rememberInfiniteTransition()
    return t.animateFloat(1f, 0f, infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse)).value
}

@Composable
fun IconActionButton(
    icon: IconSource,
    contentDescription: String,
    indicateActive: Boolean = false,
    requiresAttention: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(10.dp)
    Box(
        Modifier
            .ifThen(!enabled) {
                alpha(0.5f)
            }
            .border(
                1.dp,
                myColors.onBackground / 10,
                shape
            )
            .ifThen(indicateActive || requiresAttention) {
                border(
                    1.dp,
                    myColors.primary / if (indicateActive) 1f else alphaFlicker(),
                    shape
                )
            }
            .clip(shape)
            .background(myColors.surface)
            .clickable(enabled = enabled) {
                onClick()
            }
            .padding(6.dp)
    ) {
        MyIcon(
            icon,
            contentDescription,
            Modifier
                .size(16.dp)
        )
    }
}