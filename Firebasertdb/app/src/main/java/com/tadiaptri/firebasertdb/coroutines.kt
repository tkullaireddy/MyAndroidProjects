package com.tadiaptri.firebasertdb

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main(){

    runBlocking {
        println("Main Program starts: ${Thread.currentThread().name}")

        launch {
            println("Line10 runs on: ${Thread.currentThread().name}")
            delay(1000)
            println("Line10 completed on: ${Thread.currentThread().name}")
            // This statement may run on another thread also.
        }
        println("Main Program Ends: ${Thread.currentThread().name}")
    }
    thread {
        println("Main Starts: ${Thread.currentThread().name}")

        runBlocking {
            launch {
                println("Line22 runs on: ${Thread.currentThread().name}")
                delay(1000)
                println("Line22 completed on: ${Thread.currentThread().name}")
                // This statement may run on another thread also.
            }
        }

        GlobalScope.launch {
            println("Line1 runs on: ${Thread.currentThread().name}")
            delay(1000)
            println("Line1 completed on: ${Thread.currentThread().name}")
            // This statement may run on another thread also.

            launch {
                println("Line3 runs on: ${Thread.currentThread().name}")
                delay(1000)
                println("Line3 completed on: ${Thread.currentThread().name}")
                // This statement may run on another thread also.
            }

            launch {
                println("Line5 runs on: ${Thread.currentThread().name}")
                delay(1000)
                println("Line5 completed on: ${Thread.currentThread().name}")
                // This statement may run on another thread also.
            }

            launch {
                println("Line2 runs on: ${Thread.currentThread().name}")
                delay(1000)
                println("Line2 completed on: ${Thread.currentThread().name}")
                // This statement may run on another thread also.
            }


            launch {
                println("Line4 runs on: ${Thread.currentThread().name}")
                delay(1000)
                println("Line4 completed on: ${Thread.currentThread().name}")
                // This statement may run on another thread also.
            }

        }

        Thread.sleep(3000)


        println("Main Completes: ${Thread.currentThread().name}")
    }

}
