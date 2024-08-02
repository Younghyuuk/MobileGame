package com.example.roguelike

import java.util.*
import javax.swing.*
import kotlin.concurrent.thread

class AssetManager {
    var successCount: Int = 0
    var errorCount: Int = 0
    private val cache = mutableMapOf<String, ImageIcon>()
    private val downloadQueue = mutableListOf<String>()

    fun queueDownload(path: String) {
        println("Queueing $path")
        downloadQueue.add(path)
    }

    fun isDone(): Boolean {
        return downloadQueue.size == successCount + errorCount
    }

    fun downloadAll(callback: () -> Unit) {
        if (downloadQueue.isEmpty()) {
            Timer().schedule(object : TimerTask() {
                override fun run() {
                    callback()
                }
            }, 10)
            return
        }

        for (path in downloadQueue) {
            val img = ImageIcon()

            println(path)

            thread {
                try {
                    img.image = ImageIcon(path).image
                    println("Loaded ${img.description}")
                    successCount++
                } catch (e: Exception) {
                    println("Error loading $path")
                    errorCount++
                } finally {
                    cache[path] = img
                    if (isDone()) {
                        callback()
                    }
                }
            }
        }
    }

    fun getAsset(path: String): ImageIcon? {
        return cache[path]
    }
}
