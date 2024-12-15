package com.es.interfazproyectoapp.screens.registerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.es.interfazproyectoapp.R
import com.es.interfazproyectoapp.screens.loginScreen.Email
import com.es.interfazproyectoapp.screens.loginScreen.Password
import com.es.interfazproyectoapp.screens.reusableFunctions.ButtonCustom

@Composable
fun RegisterScreen(
    registerScreenViewModel: RegisterScreenViewModel,
    onRegisterClick: () -> Unit,
    onCancelClick: () -> Unit
){

    val name by registerScreenViewModel.name.collectAsState()
    val email by registerScreenViewModel.email.collectAsState()
    val password by registerScreenViewModel.password.collectAsState()

    val isValidName by registerScreenViewModel.isValidName.collectAsState()
    val isValidEmail by registerScreenViewModel.isValidEmail.collectAsState()
    val isValidPassword by registerScreenViewModel.isValidPassword.collectAsState()

    val isVisiblePassword by registerScreenViewModel.isVisiblePassword.collectAsState()



    // Interfaz de usuario
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Nombre
        Name(
            name = name,
            nameChange = { registerScreenViewModel.onNameChange(it) },
            isValid = isValidName
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Correo electrónico
        Email(
            email = email,
            emailChange = { registerScreenViewModel.onEmailChange(it) },
            isValid = isValidEmail
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Contraseña
        Password(
            password = password,
            passwordChange = { registerScreenViewModel.onPasswordChange(it) },
            isValidPassword = isValidPassword,
            passwordVisible = isVisiblePassword,
            passwordVisibleChange = { registerScreenViewModel.onPasswordVisibleChange() }
        )

        Spacer(modifier = Modifier.height(24.dp))


        // Botón de Registro
        ButtonCustom(
            text = "Register",
            onClick = onRegisterClick,
            isEnabled = registerScreenViewModel.validateForm(),
        )

        // Boton cancelar
        ButtonCustom(
            text = "Cancel",
            onClick = onCancelClick,
            isEnabled = true,
        )

    }
}


@Composable
fun Name(
    name: String,
    nameChange: (String) -> Unit,
    isValid: Boolean
){

    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center
    ){

        Text(
            text = "Name",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 12.sp
        )

        Spacer(Modifier.height(5.dp))

        OutlinedTextField(
            value = name,
            onValueChange = nameChange,
            placeholder = { Text("Name") },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            colors = if(isValid){
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = colorResource(R.color.gray)
                )
            }else{
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.red),
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = colorResource(R.color.gray),

                    )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = it.isFocused }

        )

        // Texto para indicar que el nombre no es valido
        if (!isValid && name.isNotEmpty() || isFocused && name.isEmpty()){
            Text(
                text = "Please enter a valid name",
                fontSize = 13.sp,
                color = colorResource(R.color.red)
            )
        }

    }

}
