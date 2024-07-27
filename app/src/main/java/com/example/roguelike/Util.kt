package com.yourappname.utils

import kotlin.math.floor
import kotlin.math.sqrt
import kotlin.math.pow

object Utils {
    // Generates a random integer between 0 (inclusive) and n (exclusive)
    fun randomInt(n: Int): Int = (Math.random() * n).toInt()

    // Generates an RGB color string
    fun rgb(r: Int, g: Int, b: Int): String = "rgb($r, $g, $b)"

    // Generates an RGBA color string
    fun rgba(r: Int, g: Int, b: Int, a: Double): String = "rgba($r, $g, $b, $a)"

    // Generates an HSL color string
    fun hsl(h: Int, s: Int, l: Int): String = "hsl($h, ${s}%, ${l}%)"

    // Calculates the distance between two points given their coordinates
    fun getDistance(p1: Point, p2: Point): Double =
        sqrt((p2.x - p1.x).pow(2) + (p2.y - p1.y).pow(2))

    // Helper class to store point coordinates
    data class Point(val x: Double, val y: Double)
}
