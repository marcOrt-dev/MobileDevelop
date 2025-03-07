package com.example.tarjetadepresentacion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.graphicsLayer
import com.example.tarjetadepresentacion.ui.theme.TarjetaDePresentacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TarjetaDePresentacionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TarjetaDePresentacion(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TarjetaDePresentacion(modifier: Modifier = Modifier) {
    // Animación para la opacidad
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    val alpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    // Estado para detectar el toque
    var isTouched by remember { mutableStateOf(false) }

    // Animación de entrada (deslizar hacia arriba)
    val slideIn = remember { Animatable(400f) }

    // Animación de rotación de inicio (solo una vez)
    val rotationZ = remember { Animatable(0f) }

    // Animación de rotación cuando se toca la tarjeta
    LaunchedEffect(isTouched) {
        if (isTouched) {

            rotationZ.snapTo(0f) // Reiniciar la rotación
            rotationZ.animateTo(
                targetValue = 360f,
                animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
            )
        }
    }

    // Animación de entrada (deslizar hacia arriba)
    LaunchedEffect(Unit) {
        slideIn.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
        )
    }


    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(
                colors = listOf(Color(0xFF6A1B9A), Color(0xFFAB47BC))
            ))
            .padding(16.dp)
            .wrapContentSize(Alignment.Center) // Centramos el contenido en la pantalla
            .clickable {
                isTouched = !isTouched // Cambiar el estado de isTouched al tocar la tarjeta
            }
    ) {
        // Contenedor de la tarjeta con borde redondeado y sombra
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    alpha = alpha,
                    translationY = slideIn.value, // Movimiento de entrada desde abajo
                    rotationZ = rotationZ.value // Rotación inicial
                )
                .background(Color.White, RoundedCornerShape(32.dp))
                .shadow(8.dp, RoundedCornerShape(32.dp))
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFE6E1FE)) // Fondo de la columna en azul claro
            ) {
                // Imagen del logo de Android
                Image(
                    painter = painterResource(id = R.drawable.android_logo),
                    contentDescription = "Logo Android",
                    modifier = Modifier
                        .size(120.dp)
                        .graphicsLayer(scaleX = scale, scaleY = scale, alpha = alpha)
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Nombre
                Text(
                    text = "Marcos Ortiz",
                    style = TextStyle(fontSize = 32.sp, color = Color.Black),
                    modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale, alpha = alpha)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Título: Desarrollador Mobile (Animación)
                Text(
                    text = "Desarrollador Mobile",
                    style = TextStyle(fontSize = 20.sp, color = Color(0xFF7B1FA2)),
                    modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale, )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Número telefónico
                Text(
                    text = "Tel: 0991234567",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                    modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale, alpha = alpha)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Correo electrónico
                Text(
                    text = "Email: pruebacorreo@homail.com",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                    modifier = Modifier.graphicsLayer(scaleX = scale, scaleY = scale, alpha = alpha)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TarjetaDePresentacionPreview() {
    TarjetaDePresentacionTheme {
        TarjetaDePresentacion()
    }
}
