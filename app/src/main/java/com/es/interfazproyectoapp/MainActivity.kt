package com.es.interfazproyectoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.es.interfazproyectoapp.screens.navBars.MyAppWithDrawer
import com.es.interfazproyectoapp.ui.theme.InterfazProyectoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkMode by rememberSaveable { mutableStateOf(true) }
            val navController = rememberNavController()

            InterfazProyectoAppTheme (darkTheme = isDarkMode) {

                // Se obtiene el innerPadding y lo pasamos al Modifier
                Scaffold(
                    content = { innerPadding -> // Este es el innerPadding de Scaffold

                        MyAppWithDrawer(
                            navController = navController,
                            isDarkMode = isDarkMode,
                            onDarkModeSwitchChange = { isDarkMode = it },
                            //modifier = Modifier.padding(innerPadding)
                        )
                    }
                )
            }
        }
    }
}

