package org.mktech.tictactoe

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform