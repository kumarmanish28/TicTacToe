package org.mktech.tictactoe

class IOSPlatform: Platform {
    override val name: String =  "iOS"
}

actual fun getPlatform(): Platform = IOSPlatform()