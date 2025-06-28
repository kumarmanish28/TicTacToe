package org.mktech.tictactoe.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val freshColor = Color(0xFF44FF78)
val staleColor = Color(0xFFFF9E44)

val primaryColor
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFF86A8FC) else Color(0xFF283556)


val headerColor
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFF0C0C0C) else Color(0xFF283556)


val surfaceColor
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFF161616) else Color(0xFFFFFFFF)


val textColor
    @Composable
    get() = if(isSystemInDarkTheme()) Color(0xFFFFFFFF) else Color(0xFF000000)

