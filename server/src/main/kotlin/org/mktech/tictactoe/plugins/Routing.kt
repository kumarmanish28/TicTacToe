package org.mktech.tictactoe.plugins

import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.mktech.tictactoe.models.TicTacToe
import org.mktech.tictactoe.socket

fun Application.configureRouting(game: TicTacToe) {
    routing {
        socket(game)
    }
}
