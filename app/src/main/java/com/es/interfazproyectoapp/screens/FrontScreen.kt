package com.es.interfazproyectoapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.es.interfazproyectoapp.R
import com.es.interfazproyectoapp.ui.theme.white


@Composable
fun FrontScreen(
    onClickButton: () -> Unit
){

    Column(
        Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HeaderFront()

        Spacer(Modifier.height(120.dp))

        BodyFront()

        ButtonFront(
            Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 30.dp),
            onClickButton
        )


    }

}

@Composable
fun HeaderFront(){

    Image(
        painter = painterResource(R.drawable.logoapp),
        contentDescription = "Logo",
        modifier = Modifier
            .padding(top = 50.dp)
            .size(200.dp)
            .clip(CircleShape)

    )

    Spacer(Modifier.height(20.dp))

    // Titulo
    Text(
        text = "Task Manager",
        fontSize = 36.sp,
        color = MaterialTheme.colorScheme.onPrimary
    )

    Spacer(Modifier.height(5.dp))

    HorizontalDivider(
        Modifier
            .padding(horizontal = 100.dp),
        2.dp,
        MaterialTheme.colorScheme.onPrimary
    )

}

@Composable
fun BodyFront(){
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FeatureItem(
                icon = Icons.Outlined.Task,
            )

            Spacer(Modifier.width(20.dp))

            FeatureItem(
                icon = Icons.Outlined.CalendarMonth,
            )

            Spacer(Modifier.width(20.dp))

            FeatureItem(
                icon = Icons.Outlined.Person,
            )

        }

        Spacer(Modifier.height(55.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Get the most out of your days thanks to getting organized with our app",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

    }

}


@Composable
fun FeatureItem(
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Icon",
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }


    }
}


@Composable
fun ButtonFront(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit
){

    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter,
    ){

        FloatingActionButton(
            onClick = onClickButton,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = white,
            modifier = Modifier
                .padding(10.dp)
                .size(60.dp)
                .clip(CircleShape)
        ) {

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Continue"
            )
        }
    }


}

@Preview
@Composable
fun PrevieewPortada(){
    FrontScreen ( {})
}