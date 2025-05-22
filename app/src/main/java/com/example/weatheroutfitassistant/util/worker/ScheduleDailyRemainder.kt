package com.example.myapplication.util.worker

import android.content.Context
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.myapplication.util.worker.WeatherReminderWorker

fun scheduleDailyReminder(context: Context) {
    val currentDateTime = Calendar.getInstance()

    val dueDate = Calendar.getInstance()
    dueDate.set(Calendar.HOUR_OF_DAY, 7)
    dueDate.set(Calendar.MINUTE, 0)
    dueDate.set(Calendar.SECOND, 0)

    if (dueDate.before(currentDateTime)) {
        dueDate.add(Calendar.HOUR_OF_DAY, 24)
    }

    val delay = dueDate.timeInMillis - currentDateTime.timeInMillis

    val workRequest = PeriodicWorkRequestBuilder<WeatherReminderWorker>(24, TimeUnit.HOURS)
        .setInitialDelay(delay, TimeUnit.MILLISECONDS)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "daily_weather_reminder",
        ExistingPeriodicWorkPolicy.REPLACE,
        workRequest
    )
}
