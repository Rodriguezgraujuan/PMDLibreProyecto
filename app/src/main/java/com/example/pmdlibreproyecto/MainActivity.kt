package com.tu.paquete.rps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdlibreproyecto.ui.theme.PMDLibreProyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PMDLibreProyectoTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    RPScreen()
                }
            }
        }
    }
}

@Composable
fun RPScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Piedra, Papel o Tijera", fontSize = 28.sp)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Elige tu jugada:")
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { /* más adelante */ }) { Text("✊ Piedra") }
                Button(onClick = { /* más adelante */ }) { Text("✋ Papel") }
                Button(onClick = { /* más adelante */ }) { Text("✌️ Tijera") }
            }
        }
        Text("Resultado aparecerá aquí")
    }
}
