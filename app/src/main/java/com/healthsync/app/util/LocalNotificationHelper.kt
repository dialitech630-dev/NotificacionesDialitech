package com.healthsync.app.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import kotlin.random.Random

object LocalNotificationHelper {

    private const val CHANNEL_ID = "demo_presentacion_canal"

    private fun crearCanal(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val canal = NotificationChannel(
                CHANNEL_ID,
                "Demostración del Proyecto",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(canal)
        }
    }

    // AHORA RECIBE EL PARÁMETRO: bpm
    fun mostrarAlertaBasica(context: Context, bpm: Int) {
        crearCanal(context)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("⚠️ Ritmo Cardíaco Crítico")
            .setContentText("Se han detectado $bpm BPM desde el smartwatch.") // <-- Texto dinámico
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(Random.nextInt(), builder.build())
    }

    // AHORA RECIBE EL PARÁMETRO: spo2
    fun mostrarAlertaDetallada(context: Context, spo2: Int) {
        crearCanal(context)

        val estiloTextoLargo = NotificationCompat.BigTextStyle()
            .bigText("Hemos detectado una caída en tu saturación de oxígeno (SpO2 al $spo2%) durante la medición. Te recomendamos notificar al enfermero en turno, sentarte y respirar profundo.") // <-- Texto dinámico

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("🩺 Caída de Oxigenación ($spo2%)") // <-- Texto dinámico
            .setStyle(estiloTextoLargo)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(Random.nextInt(), builder.build())
    }

    // Este se queda igual, no necesita datos del reloj
    fun mostrarRecordatorioConBotones(context: Context) {
        crearCanal(context)
        val intentVacio = PendingIntent.getActivity(
            context, 0, Intent(), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_agenda)
            .setContentTitle("💊 Recordatorio de Medicación")
            .setContentText("Es hora de tomar tu antihipertensivo.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .addAction(android.R.drawable.checkbox_on_background, "Ya la tomé", intentVacio)
            .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Posponer 10 min", intentVacio)
            .setAutoCancel(true)

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(Random.nextInt(), builder.build())
    }
}