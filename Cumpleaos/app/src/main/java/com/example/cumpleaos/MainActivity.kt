package com.example.cumpleaos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cumpleaos.ui.theme.CumpleañosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CumpleañosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BirthdayCard(
                        message = "Happy Birthday Marcos!",
                        from = "From Vicente Jackare Saenger",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BirthdayCard(message: String, from: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(R.drawable.androidparty),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Sirve para cubrir toda la pantalla
        )

        // Tarjeta de felicitación
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}

// Diseño mejorado de la tarjeta de cumpleaños
@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .shadow(10.dp, RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF7B1FA2), Color(0xFF6200EE))
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .border(3.dp, Color.White, RoundedCornerShape(20.dp))
                .padding(32.dp)
        ) {
            Text(
                text = message,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    letterSpacing = 1.sp
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

                Text(
                text = from,
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "My Birthday Preview"
)
@Composable
fun BirthdayCardPreview() {
    CumpleañosTheme {
            BirthdayCard(message = "Happy Birthday Marcos!", from = "From Jacka")
    }
}
