package org.mktech.tictactoe.data

import org.jetbrains.compose.resources.DrawableResource
import org.mktech.tictactoe.AppRoutes
import tictactoe.composeapp.generated.resources.Res
import tictactoe.composeapp.generated.resources.compose_multiplatform

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: DrawableResource
)

val bottomNavItems = listOf(
    BottomNavItem(AppRoutes.GAME_STATE, "Game", Res.drawable.compose_multiplatform),
    BottomNavItem(AppRoutes.SETTINGS, "Settings", Res.drawable.compose_multiplatform),
    BottomNavItem(AppRoutes.PROFILE, "Profile", Res.drawable.compose_multiplatform),
)