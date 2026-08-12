package com.healthsync.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.healthsync.app.util.LocalNotificationHelper

@Composable
fun SimuladorNotificacionesPanel() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Panel de Demostración (Offline)",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            // Le pasamos un 135 simulado para que no marque error
            onClick = { LocalNotificationHelper.mostrarAlertaBasica(context, 135) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Text("Simular Alerta Rápida (BPM)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            // Le pasamos un 82 simulado para que no marque error
            onClick = { LocalNotificationHelper.mostrarAlertaDetallada(context, 82) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simular Alerta Detallada (SpO2)")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            // Este se queda igual, no necesita datos médicos
            onClick = { LocalNotificationHelper.mostrarRecordatorioConBotones(context) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Simular Recordatorio con Botones")
        }
    }
}