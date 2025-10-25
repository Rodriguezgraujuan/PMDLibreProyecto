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

fun determineWinner(player: Move, cpu: Move): String {
    if (player == cpu) return "Empate"
    return when (player) {
        Move.ROCK -> if (cpu == Move.SCISSORS) "Ganas" else "Pierdes"
        Move.PAPER -> if (cpu == Move.ROCK) "Ganas" else "Pierdes"
        Move.SCISSORS -> if (cpu == Move.PAPER) "Ganas" else "Pierdes"
    }
}
@Composable
fun RPScreen() {
    var playerMove by remember { mutableStateOf<Move?>(null) }
    var cpuMove by remember { mutableStateOf<Move?>(null) }
    var message by remember { mutableStateOf("Elige tu jugada") }
    var playerScore by remember { mutableIntStateOf(0) }
    var cpuScore by remember { mutableIntStateOf(0) }

    fun play(move: Move) {
        playerMove = move
        cpuMove = Move.random()
        when (determineWinner(move, cpuMove!!)) {
            "Ganas" -> {
                playerScore++
                message = "¡Ganaste! ${move.emoji} vence a ${cpuMove!!.emoji}"
            }
            "Pierdes" -> {
                cpuScore++
                message = "Perdiste. ${cpuMove!!.emoji} vence a ${move.emoji}"
            }
            else -> {
                message = "Empate. Ambos eligieron ${move.emoji}"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Piedra, Papel o Tijera", fontSize = 28.sp)

        Row(horizontalArrangement = Arrangement.spacedBy(24.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Jugador", fontSize = 16.sp)
                Text(playerMove?.emoji ?: "-", fontSize = 48.sp)
                Text(playerMove?.label ?: "")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("CPU", fontSize = 16.sp)
                Text(cpuMove?.emoji ?: "-", fontSize = 48.sp)
                Text(cpuMove?.label ?: "")
            }
        }


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Elige tu jugada:")
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { play(Move.ROCK) }) { Text("✊ Piedra") }
                Button(onClick = { play(Move.PAPER) }) { Text("✋ Papel") }
                Button(onClick = { play(Move.SCISSORS) }) { Text("✌️ Tijera") }
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Jugador: ${playerMove?.emoji ?: "-"}")
            Text("CPU: ${cpuMove?.emoji ?: "-"}")
        }

        Text(message)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { playerScore = 0; cpuScore = 0; playerMove = null; cpuMove = null; message = "Reiniciado" }) {
                Text("Reiniciar")
            }
        }
    }
}
