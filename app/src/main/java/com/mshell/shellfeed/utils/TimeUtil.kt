package com.mshell.shellfeed.utils

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object TimeUtil {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
        .withLocale(Locale.ENGLISH)
    fun convertToDateString(dateTime: String?): String {
        if (dateTime.isNullOrBlank()) return "-"
        return try {
            val instant = Instant.parse(dateTime)
            val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            localDate.format(formatter)
        } catch (e: Exception) {
            "-"
        }
    }

    fun getTimeAgo(dateTime: String?): String {
        val instant = Instant.parse(dateTime)
        val duration = Duration.between(instant, Instant.now())
        val minutesAgo = duration.toMinutes()

        return when {
            minutesAgo < 1 -> "Just now"
            minutesAgo < 60 -> "$minutesAgo minutes ago"
            minutesAgo < 1440 -> "${minutesAgo / 60} hours ago"
            else -> convertToDateString(dateTime)
        }
    }
}