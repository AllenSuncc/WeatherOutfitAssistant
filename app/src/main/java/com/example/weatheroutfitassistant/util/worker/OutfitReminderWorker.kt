package com.example.myapplication.util.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.myapplication.R
import com.example.myapplication.data.remote.RetrofitInstance
import com.example.myapplication.util.weather.getClothingSuggestion

class WeatherReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val response = RetrofitInstance.api.getWeatherByCoordinates(
                lat = -37.8136, // Melbourne
                lon = 144.9631,
                apiKey = "007e091705ae5a42604ef90d694ad695" // 替换成你自己的
            )

            val temp = response.main.temp
            val description = response.weather.firstOrNull()?.description ?: "No description"
            val suggestion = getClothingSuggestion(temp, description)

            sendNotification("Outfit Reminder", "$description, $temp°C. $suggestion")

            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }

    private fun sendNotification(title: String, message: String) {
        val channelId = "weather_channel"
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Weather Alerts", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_clear)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}
