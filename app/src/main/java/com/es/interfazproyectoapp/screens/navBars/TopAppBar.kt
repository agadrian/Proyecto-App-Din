package com.es.interfazproyectoapp.screens.navBars

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.es.interfazproyectoapp.ui.theme.black
import com.es.interfazproyectoapp.ui.theme.white
import kotlin.math.max



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onMenuClick: () -> Unit,

){
    Column {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = "GPS Tracker",
                    maxLines = 1
                )
            },

            // Flecha para volver hacia detras
            navigationIcon = {
                IconButton(
                    onClick = {
                        if (!navController.navigateUp()) {
                            navController.popBackStack()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Go back",
                        tint = white
                    )
                }
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = black,
                titleContentColor = white
            ),
            // Iconos que se colocan al final de la barra
            actions = {

                // Icono menun hamurguesa
                IconButton(
                    onClick = onMenuClick
                ) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = white
                    )
                }
            }
        )
        HorizontalDivider(
            color = white,
            thickness = 0.1.dp
        )

    }
}

