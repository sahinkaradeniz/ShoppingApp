package com.skapps.eys.Util

import java.text.SimpleDateFormat
import java.util.*

fun getRandUid(n: Int): String {
    val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val random = Random(System.nanoTime())
    val password = StringBuilder()
    for (i in 0 until n) {
        val rIndex = random.nextInt(characterSet.length)
        password.append(characterSet[rIndex])
    }
    return password.toString()
}
fun getCurrentDate(): String {
    val calForDate = Calendar.getInstance()
    val currentDate = SimpleDateFormat("dd-MM-yy / hh:mm a")
    return currentDate.format(calForDate.time)
}

fun getCurrentTime(): String {
    val calForTime = Calendar.getInstance()
    val currentTime = SimpleDateFormat("hh:mm a")
    return currentTime.format(calForTime.time)
}