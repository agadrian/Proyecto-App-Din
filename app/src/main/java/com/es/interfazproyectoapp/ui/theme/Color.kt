package com.es.interfazproyectoapp.ui.theme

import androidx.compose.ui.graphics.Color

/*
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
*/


val black = hexToColor("#FF000000")
val white = hexToColor("#FFFFFFFF")
val blue = hexToColor("#5191bf")
val blueContainer = hexToColor("#194063")


/**
 * Extra añadido. Funcion para convertir colores HEX a Color
 */

fun hexToColor(hex: String): Color {
    val color = if (hex.startsWith("#")) {
        hex.substring(1)
    } else {
        hex
    }

    val colorValue = if (color.length == 6) "FF$color" else color // Prevenir colores RGBA

    // Hex -> Int -> Color
    return Color(android.graphics.Color.parseColor("#$colorValue"))
}