package org.mktech.tictactoe.di

import org.koin.core.context.startKoin
import kotlin.experimental.ExperimentalObjCName

fun initKoin() {
    startKoin {
        modules(
           commonModule
        )
    }
}

// Helper object for Swift interop
class KoinHelper {
    @OptIn(ExperimentalObjCName::class)
    @ObjCName("doInitKoin")
    fun doInitKoin() {
        initKoin()
    }
}