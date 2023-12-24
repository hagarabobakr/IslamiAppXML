package com.example.islamiappxml

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.getSystemService

class   MyApplication : Application() {
    companion object{
        val RADIO_PLAYER_NOTIFICATION_CHANNEL = "radio channel"
    }

    override fun onCreate() {
        super.onCreate()
        creatNotificationChannel()
    }

    private fun creatNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.app_name)
            val desc = getString(R.string.radio)
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(RADIO_PLAYER_NOTIFICATION_CHANNEL,name,importance)
                .apply {
                    description = desc
                }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }


    }
}