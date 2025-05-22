package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.myapplication.ui.theme.MyApplicationTheme
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import com.example.myapplication.navigation.BottomNavigationBar
import com.example.myapplication.util.worker.WeatherReminderWorker
import com.example.myapplication.util.worker.scheduleDailyReminder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1001)
        }
        // 调度每日提醒任务
        //scheduleDailyReminder(this)
        // 测试 WorkManager 是否能正常运行
        val testRequest = OneTimeWorkRequestBuilder<WeatherReminderWorker>().build()
        WorkManager.getInstance(this).enqueue(testRequest)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BottomNavigationBar()
                }
            }
        }
    }
}


