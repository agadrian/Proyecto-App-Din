package com.es.interfazproyectoapp.screens.loginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

@Composable
fun Header(

){


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp, bottom = 10.dp, start = 40.dp, end = 40.dp)
    ){

        Text(
            text = "Already have an",
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )

        //Spacer(Modifier.height(5.dp))

        Text(
            text = "Account?",
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}


@Preview
@Composable
fun PreviewHeadr(){
    LoginScreen(
        LoginScreenViewModel(),
        rememberNavController(),
        {},
        {}
    )
}