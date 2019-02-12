package com.gdn.rentalan.util

import android.R
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(EXTRA_TYPE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val title = if (type.equals(TYPE_ONE_TIME, ignoreCase = true)) TYPE_ONE_TIME else TYPE_REPEATING
        val notifId = if (type.equals(TYPE_ONE_TIME, ignoreCase = true)) ID_ONETIME else ID_REPEATING

//        showToast(context, title, message)
        showAlarmNotification(context, title, message, notifId)
        //Jika Anda ingin menampilkan dengan Notif anda bisa menghilangkan komentar pada baris dibawah ini.
        //showAlarmNotification(context, title, message, notifId);
    }

    // Gunakan metode ini untuk menampilkan notifikasi
    private fun showAlarmNotification(context: Context, title: String, message: String, notifId: Int) {
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "AlarmManager channel"

        val notificationManagerCompat = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_btn_speak_now)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .setSound(alarmSound)

        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            /* Create or update. */
            val channel = NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)

            notificationManagerCompat?.createNotificationChannel(channel)
        }

        val notification = builder.build()

        notificationManagerCompat?.notify(notifId, notification)

    }

    // Metode ini digunakan untuk menjalankan alarm one time

    fun setOneTimeAlarm(context: Context, type: String, date: String, time: String, message: String) {

        // Validasi inputan date dan time terlebih dahulu
        if (isDateInvalid(date, DATE_FORMAT) || isDateInvalid(time, TIME_FORMAT)) return

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        Log.e("ONE TIME", "$date $time")
        val dateArray = date.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, Integer.parseInt(dateArray[0]))
        calendar.set(Calendar.MONTH, Integer.parseInt(dateArray[1]) - 1)
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateArray[2]))
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_ONETIME, intent, 0)
        alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent)

        Toast.makeText(context, "One time alarm set up", Toast.LENGTH_SHORT).show()
    }

    // Metode ini digunakan untuk validasi date dan time
    fun isDateInvalid(date: String, format: String): Boolean {
        try {
            val df = SimpleDateFormat(format, Locale.getDefault())
            df.setLenient(false)
            df.parse(date)
            return false
        } catch (e: ParseException) {
            return true
        }

    }

    companion object {
        val TYPE_ONE_TIME = "OneTimeAlarm"
        val TYPE_REPEATING = "RepeatingAlarm"
        val EXTRA_MESSAGE = "message"
        val EXTRA_TYPE = "type"

        // Siapkan 2 id untuk 2 macam alarm, onetime dna repeating
        private val ID_ONETIME = 100
        private val ID_REPEATING = 101

        private val DATE_FORMAT = "yyyy-MM-dd"
        private val TIME_FORMAT = "HH:mm"
    }

}