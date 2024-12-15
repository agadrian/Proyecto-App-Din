package com.es.interfazproyectoapp.navegation

sealed class AppScreen(
    val route: String
) {
    object FrontScreen: AppScreen("FrontScreen")
    object LoginScreen : AppScreen("LoginScreen") {
        /**
         * Permite navegar directamente a la ruta especificada
         */
        fun createRoute(
            email: String
        ): String {
            return "LoginScreen"
        }
    }
    object MainScreen : AppScreen("MainScreen")
    object TasksScreen : AppScreen("TasksScreen")
    object CalendarScreen : AppScreen("CalendarScreen")
    object UserScreen : AppScreen("UserScreen")

    /* Menu hamburguesa */
    object CreateUserScreen : AppScreen("CreateUserScreen")
    object CreateTaskScreen : AppScreen("CreateTaskScreen")
    object AboutUsScreen : AppScreen("AboutUsScreen")
    object PrivacyPolicyScreen : AppScreen("PrivacyPolicyScreen")

    /* Otras */
    object RegisterScreen : AppScreen("RegisterScreen")

}