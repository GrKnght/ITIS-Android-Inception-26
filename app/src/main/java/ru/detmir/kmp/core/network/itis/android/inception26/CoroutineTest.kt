package ru.detmir.kmp.core.network.itis.android.inception26

import kotlinx.coroutines.delay

class CoroutineTest {

    suspend fun sampleFun() {
        delay(2000L)
        println("TEST TAG - Hello from suspend function!")
    }

    suspend fun sumArgs(arg1: Int, arg2: Int): Int {
        delay(1000L)
        val sum = arg1.plus(arg2)
        println("TEST TAG - Sum result $sum")
        return sum
    }

    suspend fun secondTestFun() {
        sampleFun()
    }
}