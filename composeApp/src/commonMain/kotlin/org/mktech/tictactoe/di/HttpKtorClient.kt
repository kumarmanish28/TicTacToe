package org.mktech.tictactoe.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(): HttpClient {
    return HttpClient(CIO) {
        install(WebSockets)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        /*  install(ContentNegotiation) {
              json(Json {
                  ignoreUnknownKeys = true
                  prettyPrint = true
                  isLenient = true
              })
          }

          install(HttpTimeout) {
              requestTimeoutMillis = 15000L
              connectTimeoutMillis = 15000L
              socketTimeoutMillis = 15000L
          }

          expectSuccess = true*/
    }
}
