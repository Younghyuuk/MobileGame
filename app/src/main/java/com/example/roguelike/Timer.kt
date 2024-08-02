package com.example.roguelike

import kotlin.system.*

class Timer {
    var gameTime: Double = 0.0
    private val maxStep: Double = 0.05
    private var lastTimestamp: Long = 0

    fun tick(): Double {
        val current: Long = getTimeMillis()
        val delta: Double = (current - lastTimestamp) / 1000.0
        lastTimestamp = current

        val gameDelta: Double = minOf(delta, maxStep)
        gameTime += gameDelta
        return gameDelta
    }
}

fun getTimeMillis(): Long {
    return System.currentTimeMillis()
}
