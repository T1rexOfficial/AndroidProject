package com.example.help_me.base

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class CoroutineProvider {

    open val IO: CoroutineContext by lazy {
        Dispatchers.IO
    }
    open val Main: CoroutineContext by lazy {
        Dispatchers.Main
    }
}