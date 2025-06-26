package org.mktech.tictactoe

import android.app.Application
import org.koin.core.context.startKoin
import org.mktech.tictactoe.di.commonModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                commonModule
            )
        }
    }
}