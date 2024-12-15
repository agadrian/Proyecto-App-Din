package com.es.interfazproyectoapp.screens.loginScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun LoginScreen(
    loginScreenViewModel: LoginScreenViewModel,
    navController: NavHostController,
    onClickRegister: () -> Unit,
    onLoginClick: () -> Unit
){


    // Datos de prueba, los dejo aqui porque no se suele hacer esto
    val correctEmail = "adri@gmail.com"
    val correctPassword = "adrian1234"

    val context = LocalContext.current
    val focusManager = androidx.compose.ui.platform.LocalFocusManager.current

    val email by loginScreenViewModel.email.collectAsState()
    val isValidEmail by loginScreenViewModel.isValidEmail.collectAsState()

    val password by loginScreenViewModel.password.collectAsState()
    val isValidPassword by loginScreenViewModel.isValidPassword.collectAsState()
    val isVisiblePassword by loginScreenViewModel.isVisiblePassword.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            //.verticalScroll(scrollState)
            .clickable { focusManager.clearFocus() } // Para quitar el foco de donde este, al clickar en el background
            .padding(bottom = 0.dp, top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()

        Spacer(Modifier.height(40.dp))

        Body(
            email = email,
            password = password,
            loginScreenViewModel = loginScreenViewModel,
            isValidPassword = isValidPassword,
            isValidEmail = isValidEmail,
            isVisiblePassword = isVisiblePassword,
            correctEmail = correctEmail,
            correctPassword = correctPassword,
            navController = navController,
            context = context,
            onLoginClick = onLoginClick
        )

        Spacer(Modifier.height(25.dp))

        Footer(
            onClcikRegister = onClickRegister
        )
    }

}

@Preview
@Composable
fun preview(){
    LoginScreen(
        LoginScreenViewModel(),
        rememberNavController(),
        {},
        {}
    )
}