package com.healthsync.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class HealthSyncMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM_TOKEN", "El token de este dispositivo es: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.let {
            Log.d("FCM_MESSAGE", "Notificación recibida: ${it.title} - ${it.body}")

            // Llamamos a la función que la dibuja en pantalla
            mostrarNotificacionVisual(it.title, it.body)
        }
    }

    private fun mostrarNotificacionVisual(titulo: String?, mensaje: String?) {
        val channelId = "alertas_vitaltrack_canal"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // En Android 8.0 y superior, es obligatorio crear un "Canal" de notificaciones
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canal = NotificationChannel(
                channelId,
                "Alertas del Sistema",
                NotificationManager.IMPORTANCE_HIGH // Importancia alta para que baje flotando
            )
            notificationManager.createNotificationChannel(canal)
        }

        // Construimos el diseño visual de la notificación
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info) // Un ícono genérico de Android por ahora
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        // Lanzamos la notificación al sistema
        notificationManager.notify(Random.nextInt(), builder.build())
    }
}