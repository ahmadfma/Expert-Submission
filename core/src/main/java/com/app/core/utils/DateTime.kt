package com.app.core.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DateTime {

    @SuppressLint("SimpleDateFormat")
    fun getPublishTime(published : String) : String {
        var publishTime = ""
        val publish = published.replace("Z", "")
        val formatPublish: String = getFormatTimePublish(publish)

        val dateStop: String = getFormatCurrentTime()

        val format = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")

        val d1: Date?
        val d2: Date?

        try {
            d1 = format.parse(formatPublish)
            d2 = format.parse(dateStop)

            //in milliseconds
            val diff: Long = d2.time - d1.time
            val diffSeconds = diff / 1000 % 60
            val diffMinutes = diff / (60 * 1000) % 60
            val diffHours = diff / (60 * 60 * 1000) % 24
            val diffDays = diff / (24 * 60 * 60 * 1000)

            publishTime = if(diffDays != 0L) {
                "$diffDays hari yang lalu"
            } else if(diffHours != 0L) {
                "$diffHours jam yang lalu"
            } else if(diffMinutes != 0L){
                "$diffMinutes menit yang lalu"
            } else {
                "$diffSeconds detik yang lalu"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return publishTime
    }

    private fun getFormatTimePublish(time: String): String {
        val arr = time.split("T")

        val date = arr[0].split("-".toRegex()).toTypedArray()
        val hour = arr[1].split(":".toRegex()).toTypedArray()

        val tempDate = date[1] + "/" + date[2] + "/" + date[0]
        val tempHour = hour[0] + ":" + hour[2] + ":" + hour[1]

        return "$tempDate $tempHour"
    }

    private fun getFormatCurrentTime(): String {
        val myDateObj: LocalDateTime = LocalDateTime.now()
        val myFormatObj: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")
        return myDateObj.format(myFormatObj)
    }

}