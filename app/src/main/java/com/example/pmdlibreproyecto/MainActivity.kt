package com.example.pmdlibreproyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pmdlibreproyecto.ui.theme.PMDLibreProyectoTheme
import kotlin.random.Random

enum class Move(val label: String, val emoji: String) {
    ROCK("Piedra","✊"),
    PAPER("Papel","✋"),
    SCISSORS("Tijera","✌️");

    companion object {
        fun random(): Move = values()[Random.nextInt(values().size)]
    }
}

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
    var playerMove by remember { mutableStateOf<Move?>(null) }
    var cpuMove by remember { mutableStateOf<Move?>(null) }
    var message by remember { mutableStateOf("Elige tu jugada") }

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
                Button(onClick = { playerMove = Move.ROCK; cpuMove = Move.random(); message = "Has elegido ${Move.ROCK.label}" }) { Text("✊ Piedra") }
                Button(onClick = { playerMove = Move.PAPER; cpuMove = Move.random(); message = "Has elegido ${Move.PAPER.label}" }) { Text("✋ Papel") }
                Button(onClick = { playerMove = Move.SCISSORS; cpuMove = Move.random(); message = "Has elegido ${Move.SCISSORS.label}" }) { Text("✌️ Tijera") }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Jugador: ${playerMove?.emoji ?: "-"}")
            Text("CPU: ${cpuMove?.emoji ?: "-"}")
        }

        Text(message)
    }
}
