package com.example.animinstradordetareas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animinstradordetareas.ui.theme.AniminstradorDeTareasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AniminstradorDeTareasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContentScreen(innerPadding)
                }
            }
        }
    }
}

@Composable
fun ContentScreen(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen centrada
            val image: Painter = painterResource(id = R.drawable.ic_task_completed)  // Reemplaza "tu_imagen" por el nombre de la imagen
            Image(
                painter = image,
                contentDescription = "Imagen centrada",
                modifier = Modifier.size(200.dp) // Ajusta el tamaño de la imagen si es necesario
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre la imagen y el texto

            // Primer texto con estilo
            Text(
                text = "All tasks completed",
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 8.dp)
            )

            // Segundo texto con tamaño de fuente 16sp
            Text(
                text = "Nice work!",
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AniminstradorDeTareasTheme {
        ContentScreen(PaddingValues())
    }
}
