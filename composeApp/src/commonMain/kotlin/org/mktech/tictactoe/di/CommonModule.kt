package org.mktech.tictactoe.di

import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.mktech.tictactoe.data.KtorRealTimeMessagingClient
import org.mktech.tictactoe.data.RealTimeMessagingClient
import org.mktech.tictactoe.presentation.TicTacToeViewModel

val commonModule = module {
    single { createHttpClient() }
    single<RealTimeMessagingClient> { KtorRealTimeMessagingClient(get()) }
    viewModel { TicTacToeViewModel(get()) }
}

fun initializeKoin() {
    startKoin {
        modules(
            commonModule
        )
    }
}