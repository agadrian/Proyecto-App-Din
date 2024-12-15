package com.es.interfazproyectoapp.screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.es.interfazproyectoapp.R
import com.es.interfazproyectoapp.ui.theme.blue


@Composable
fun Footer(
    onClcikRegister: () -> Unit
){

    var isPressedRegister by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        ClickableTextUnderline(
            text = "New user? Register Now",
            onClick = onClcikRegister,
            isPressed = isPressedRegister,
            onPressedChange = { isPressedRegister = it },
            colorUnpressed = MaterialTheme.colorScheme.onPrimary,
            colorPressed = blue,
        )

        Spacer(Modifier.height(70.dp))

        OtherMethod()

        Spacer(Modifier.height(20.dp))

        ImagesOtherMethods()

    }
}


@Composable
fun ClickableTextUnderline(
    text: String,
    onClick: () -> Unit,
    isPressed: Boolean,
    onPressedChange: (Boolean) -> Unit,
    colorUnpressed: Color,
    colorPressed: Color
){


    //var isPressed by remember { mutableStateOf(false) }

    Text(
        text = text,
        textDecoration = TextDecoration.Underline,
        fontSize = 15.sp,
        color = if (isPressed){
            colorPressed
        } else {
            colorUnpressed
        },
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .pointerInput(Unit){ // Con clicable, no he conseguido compatibilizar que navege y que cambie de color, con este si
                detectTapGestures (
                    onPress = {
                        onPressedChange(true)
                        tryAwaitRelease()
                        onClick()
                        onPressedChange(false)
                    }
                )
            }

    )
}


@Composable
fun OtherMethod(){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        // Linea izquierda
        Column(
            Modifier
                .weight(0.3f)
                .padding(end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            HorizontalDivider(
                Modifier
                    .padding(horizontal = 0.dp),
                1.dp,
                MaterialTheme.colorScheme.onPrimary
            )
        }

        // Texto central
        Column(
            Modifier
                .weight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "Use other Method",
                fontWeight = FontWeight.Bold,
            )
        }

        // Linea derecha
        Column(
            Modifier
                .weight(0.3f)
                .padding(start = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            HorizontalDivider(
                Modifier
                    .padding(horizontal = 0.dp),
                1.dp,
                MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun ImagesOtherMethods(){
    Row( Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(R.drawable.google),
            contentDescription = "Logo Google",
            modifier = Modifier
                .size(36.dp)

        )

        Spacer(Modifier.width(15.dp))

        Image(
            painter = painterResource(R.drawable.facebook),
            contentDescription = "Logo Facebook",
            modifier = Modifier
                .size(36.dp)

        )

    }

}

@Preview
@Composable
fun previewLogin(){
    LoginScreen(
        LoginScreenViewModel(),
        rememberNavController(),
        {},
        {}
    )
}
