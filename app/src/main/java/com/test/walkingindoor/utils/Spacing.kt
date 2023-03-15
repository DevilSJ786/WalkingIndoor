package com.test.walkingindoor.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(

    val minSmall: Dp = 2.dp,
    val extraSmall: Dp = 4.dp,
    val extraSmall1: Dp = 7.5.dp,
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val extraMedium: Dp = 24.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 48.dp,
    val extraLarge2: Dp = 64.dp,
    val extraLarge3: Dp = 86.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val spacing: Spacing
    @Composable @ReadOnlyComposable get() = LocalSpacing.current