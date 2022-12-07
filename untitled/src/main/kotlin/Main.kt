package ru.netology.deadlock

import kotlin.concurrent.thread

fun main() {
    val resourceA = Any()
    val resourceB = Any()

    val consumerA = Consumer("A")
    val consumerB = Consumer("B")

    val t1 = thread {
        consumerA.lockFirstAndTrySecond(resourceA, resourceB)
    }
    val t2 = thread {
        consumerB.lockFirstAndTrySecond(resourceA, resourceB)
    }

    t1.join()
    t2.join()

    println("main successfully finished")
}

class Consumer(private val name: String) {
    fun lockFirstAndTrySecond(first: Any, second: Any) {
        synchronized(first) {
            println("$name locked first, sleep and wait for second")
            Thread.sleep(1000)
            lockSecond(second)
        }
    }

    fun lockSecond(second: Any) {
        synchronized(second) {
            println("$name locked second")
        }
    }
}