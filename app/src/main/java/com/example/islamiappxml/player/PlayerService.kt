package com.example.islamiappxml.player

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.example.islamiappxml.MyApplication
import com.example.islamiappxml.R

class PlayerService : Service() {
    var mediaPlayer = MediaPlayer()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val urlToPlay = intent?.getStringExtra("Url")
        val name = intent?.getStringExtra("name")
        val action = intent?.getStringExtra("action")

        if (urlToPlay != null && name != null)
            srartMadiaPlayer(urlToPlay, name)

        if (action != null) {
            Log.e("action", action)

            if (action.equals(PLAY_ACTION)) {
                playPousMediaPlayer()
            } else if (action.equals(STOP_ACTION)) {
                stopPousMediaPlayer()
            }
        }
        return START_NOT_STICKY
    }

    private fun stopPousMediaPlayer() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.reset()
        }
        stopForeground(true)
        stopSelf()

    }

    var name: String = ""

    private fun playPousMediaPlayer() {
        Log.e("action", "play pause")

        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else if (!(mediaPlayer.isPlaying)) {
            mediaPlayer.start()
        }
        updateNotification()
    }

    val RADIO_PLAYER_NOTIFICATION_ID = 20
    private fun updateNotification() {
        val defultView = RemoteViews(packageName, R.layout.notification_defult_view)

        defultView.setTextViewText(R.id.title_notifiction, "Islami App Radio")
        defultView.setTextViewText(R.id.des_notifiction, name)
        defultView.setImageViewResource(
            R.id.img_notifiction,
            if (mediaPlayer.isPlaying) R.drawable.ic_stop else R.drawable.ic_play
        )
        defultView.setOnClickPendingIntent(R.id.ic_play, getPlayButtonPendingIntent())
        defultView.setOnClickPendingIntent(R.id.ic_stop, getStopButtonPendingIntent())
        var builder = NotificationCompat.Builder(this,
            MyApplication.RADIO_PLAYER_NOTIFICATION_CHANNEL
        )
            .setSmallIcon(R.drawable.ic_round_circle_notifications_24)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(defultView)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setDefaults(0)
            .setSound(null)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
        notificationManager.notify(RADIO_PLAYER_NOTIFICATION_ID, builder.build())
    }

    val STOP_ACTION = "stop"
    val PLAY_ACTION = "play"
    private fun getStopButtonPendingIntent(): PendingIntent? {
        val intent = Intent(this, PlayerService::class.java)
        intent.putExtra("action", PLAY_ACTION)
        val pendingIntent = PendingIntent.getService(
            this,
            12, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        return pendingIntent
    }

    private fun getPlayButtonPendingIntent(): PendingIntent? {
        val intent = Intent(this, PlayerService::class.java)
        intent.putExtra("action", STOP_ACTION)
         val pendingIntent = PendingIntent.getService(
            this,
            0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        return pendingIntent

    }

   fun srartMadiaPlayer(urlToPlay: String, name: String) {
        pauseMadiaPlayer()
        this.name = name
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(this, Uri.parse(urlToPlay))
        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener {
            it.start()
        }
        creatNotificatioForMediaPlayer(name)
    }
    fun pauseMadiaPlayer() {
        if (mediaPlayer.isPlaying)
            mediaPlayer.pause()
    }

    private fun creatNotificatioForMediaPlayer(name: String) {
        val defultView = RemoteViews(packageName, R.layout.notification_defult_view)
        defultView.setTextViewText(R.id.title_notifiction,
            MyApplication.RADIO_PLAYER_NOTIFICATION_CHANNEL
        )
        defultView.setTextViewText(R.id.des_notifiction, name)
        defultView.setImageViewResource(
            R.id.img_notifiction,
            if (mediaPlayer.isPlaying) R.drawable.ic_stop else R.drawable.ic_play
        )
        defultView.setOnClickPendingIntent(R.id.ic_play, getPlayButtonPendingIntent())
        defultView.setOnClickPendingIntent(R.id.ic_stop, getStopButtonPendingIntent())
        var builder = NotificationCompat.Builder(this, "Islami")
            .setSmallIcon(R.drawable.ic_round_circle_notifications_24)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(defultView)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setSound(null)
        startForeground(RADIO_PLAYER_NOTIFICATION_ID, builder.build())


    }

    val binder = MyBinder()
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    inner class MyBinder : Binder() {
        public fun getService(): PlayerService {
            return this@PlayerService
        }
    }
}