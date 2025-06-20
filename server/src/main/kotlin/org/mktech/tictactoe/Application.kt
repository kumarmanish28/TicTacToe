package org.mktech.tictactoe

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.mktech.tictactoe.models.TicTacToe
import org.mktech.tictactoe.plugins.configureMonitoring
import org.mktech.tictactoe.plugins.configureRouting
import org.mktech.tictactoe.plugins.configureSerialization
import org.mktech.tictactoe.plugins.configureSockets

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

@Suppress("unused")
fun Application.module() {
    val game = TicTacToe()
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureRouting(game)
}