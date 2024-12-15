package com.es.interfazproyectoapp.screens.loginScreen

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.es.interfazproyectoapp.R
import com.es.interfazproyectoapp.screens.reusableFunctions.ButtonCustom
import com.es.interfazproyectoapp.utils.customToast


@Composable
fun Body(
    email: String,
    password: String,
    loginScreenViewModel: LoginScreenViewModel,
    isValidPassword: Boolean,
    isValidEmail: Boolean,
    isVisiblePassword: Boolean,
    correctEmail: String,
    correctPassword: String,
    navController: NavController,
    context: Context,
    onLoginClick: () -> Unit



){
    Email(
        email = email,
        emailChange = {
            loginScreenViewModel.onEmailChange(it)
        },
        isValid = isValidEmail

    )

    Spacer(Modifier.height(15.dp))

    Password(
        password = password,
        passwordChange = {
            loginScreenViewModel.onPasswordChange(it)
        },
        passwordVisible = isVisiblePassword,
        passwordVisibleChange = { loginScreenViewModel.onPasswordVisibleChange() },
        isValidPassword = isValidPassword
    )

    Spacer(Modifier.height(15.dp))


    ButtonCustom(
        text = "Log In",
        onClick = {
            // Si es válido, navega a MainScreen (se gestiona elevadamente en AppNavigation)
            if (loginScreenViewModel.validateLogin(correctEmail, correctPassword)){
                onLoginClick()
                loginScreenViewModel.clearInputs()
            }else{
                customToast(context, "Wrong email or password")
            }
        },
        //enabled = isValidEmail && isValidPassword,  --> Lo dejo siempre habilitado ya que muestro mensajes constantes de si es valido o no,
        isEnabled = true
    )
}


@Composable
fun Email(
    email: String,
    emailChange: (String) -> Unit,
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
            text = "Email",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 12.sp
        )

        Spacer(Modifier.height(5.dp))

        OutlinedTextField(
            value = email,
            onValueChange = emailChange,
            placeholder = { Text("Email") },
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

        // Texto para indicar que el email no es valido
        if (!isValid && email.isNotEmpty() || isFocused && email.isEmpty()){
            Text(
                text = "Please enter a valid email address",
                fontSize = 13.sp,
                color = colorResource(R.color.red)
            )
        }

    }

}


/**
 * Textfield parala contraseña
 */
@Composable
fun Password(
    password: String,
    passwordChange: (String) -> Unit,
    passwordVisible: Boolean,
    passwordVisibleChange: () -> Unit,
    isValidPassword: Boolean
){

    val focusRequester = remember { FocusRequester() }
    var isTextFieldFocused by remember { mutableStateOf(false) }
    var isEyeFocused by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Password",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 12.sp
        )

        Spacer(Modifier.height(5.dp))

        OutlinedTextField(
            value = password,
            onValueChange = passwordChange,
            placeholder = { Text("Password") },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            trailingIcon = {
                val image = if(passwordVisible){
                    Icons.Filled.VisibilityOff
                }else{
                    Icons.Filled.Visibility
                }


                IconButton(
                    onClick = passwordVisibleChange,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged { isEyeFocused = it.isFocused }
                ) {
                    Icon(
                        imageVector = image,
                        contentDescription = "See password",
                        tint = if (isEyeFocused) colorResource(R.color.white) else colorResource(R.color.gray)
                    )
                }
            },

            // Cambio del ojito para ver/ocultar la contraseña
            visualTransformation = if(passwordVisible){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { isTextFieldFocused = it.isFocused },

            colors = if(isValidPassword){
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = colorResource(R.color.gray)
                )
            }else{
                OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(R.color.red),
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedBorderColor = colorResource(R.color.gray)
                )
            }
        )

        // Texto para indicar que la contraseña no es valida
        if (!isValidPassword && password.isNotEmpty() || isTextFieldFocused && password.isEmpty()){
            Text(
                text = "Please enter a valid password (8+ characters)",
                fontSize = 13.sp,
                color = colorResource(R.color.red)
            )
        }
    }
}



@Preview
@Composable
fun PreviewBodyLogin(){
    Body(
        "",
        "",
        LoginScreenViewModel(),
        false,
        false,
        false,
        "",
        "",
        rememberNavController(),
        LocalContext.current,
        {}
    )
}