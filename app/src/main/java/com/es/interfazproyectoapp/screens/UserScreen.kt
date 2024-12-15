package com.es.interfazproyectoapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp


@Composable
fun UserScreen(){

    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "User Screen",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 25.sp
        )

        Text(
            text = "Available Soon...",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 25.sp
        )

    }
}