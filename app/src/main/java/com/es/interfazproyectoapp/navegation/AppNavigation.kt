package com.es.interfazproyectoapp.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.es.interfazproyectoapp.screens.AboutUsScreen
import com.es.interfazproyectoapp.screens.CalendarScreen
import com.es.interfazproyectoapp.screens.CreateTaskScreen
import com.es.interfazproyectoapp.screens.CreateUserScreen
import com.es.interfazproyectoapp.screens.FrontScreen
import com.es.interfazproyectoapp.screens.loginScreen.LoginScreen
import com.es.interfazproyectoapp.screens.MainScreen
import com.es.interfazproyectoapp.screens.PrivacyPolicyScreen
import com.es.interfazproyectoapp.screens.registerScreen.RegisterScreen
import com.es.interfazproyectoapp.screens.TasksScreen
import com.es.interfazproyectoapp.screens.UserScreen
import com.es.interfazproyectoapp.screens.loginScreen.LoginScreenViewModel
import com.es.interfazproyectoapp.screens.registerScreen.RegisterScreenViewModel


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
){
    //val navControlador = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = AppScreen.FrontScreen.route
    ) {

        // Ruta especifica de FrontScreen
        composable(
            route = AppScreen.FrontScreen.route
        ) {
            FrontScreen(
                onClickButton = {
                    navController.navigate(AppScreen.LoginScreen.route)
                }
            )
        }


        // Ruta especifica de LoginScreen
        composable (
            route = AppScreen.LoginScreen.route,
        ) {
            LoginScreen(
                loginScreenViewModel = LoginScreenViewModel(),
                navController = navController,
                onClickRegister = {
                    navController.navigate(AppScreen.RegisterScreen.route)
                },
                onLoginClick = {
                    navController.navigate(AppScreen.MainScreen.route)
                }
            )
        }

        // Ruta especifica de MainScreen
        composable (
            route = AppScreen.MainScreen.route,
        ) {
            MainScreen()
        }


        // Ruta especifica de TasksScreen
        composable (
            route = AppScreen.TasksScreen.route,
        ) {
            TasksScreen()
        }


        // Ruta especifica de CalendarScreen
        composable (
            route = AppScreen.CalendarScreen.route,
        ) {
            CalendarScreen()
        }


        // Ruta especifica de UserScreen
        composable (
            route = AppScreen.UserScreen.route,
        ) {
            UserScreen()

        }


        /* Menu hammburguesa opciones */

        // Ruta especifica de CreateUserScreen
        composable (
            route = AppScreen.CreateUserScreen.route,
        ) {
            CreateUserScreen()

        }


        // Ruta especifica de CreateTaskScreen
        composable (
            route = AppScreen.CreateTaskScreen.route,
        ) {
            CreateTaskScreen()

        }

        // Ruta especifica de AboutUsScreen
        composable (
            route = AppScreen.AboutUsScreen.route,
        ) {
            AboutUsScreen()

        }


        // Ruta especifica de PrivacyPolicyScreen
        composable (
            route = AppScreen.PrivacyPolicyScreen.route,
        ) {
            PrivacyPolicyScreen()
        }


        /* Otras */

        // Ruta especifica de RegisterScreen
        composable (
            route = AppScreen.RegisterScreen.route,
        ) {
            RegisterScreen(
                registerScreenViewModel = RegisterScreenViewModel(),
                onRegisterClick = {
                    navController.navigate(AppScreen.MainScreen.route)
                },
                onCancelClick = {
                    if (!navController.navigateUp()) {
                        navController.popBackStack()
                    }
                }
            )
        }




    }
}
