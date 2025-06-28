package org.mktech.tictactoe

import android.app.Application
import org.koin.core.context.startKoin
import org.mktech.tictactoe.di.commonModule
import org.mktech.tictactoe.di.initializeKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}