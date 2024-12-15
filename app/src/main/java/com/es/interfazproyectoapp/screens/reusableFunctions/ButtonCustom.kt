package com.es.interfazproyectoapp.screens.reusableFunctions

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.es.interfazproyectoapp.R
import com.es.interfazproyectoapp.ui.theme.blue
import com.es.interfazproyectoapp.ui.theme.blueContainer



@Composable
fun ButtonCustom(
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean,
    modifier: Modifier = Modifier
){

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val sizeScale by animateFloatAsState(if (isPressed) 0.98f else 1f, label = "Size effect")


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = onClick,
            enabled = isEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isPressed){
                    blueContainer
                }else{
                    blue
                },
                contentColor = colorResource(R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp, vertical = 1.dp)
                .graphicsLayer(
                    scaleX = sizeScale,
                    scaleY = sizeScale
                ),
            interactionSource = interactionSource
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.Bold
            )
        }
    }

}