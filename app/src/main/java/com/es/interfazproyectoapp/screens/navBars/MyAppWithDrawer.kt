package com.es.interfazproyectoapp.screens.navBars

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.es.interfazproyectoapp.R
import com.es.interfazproyectoapp.model.NavigationItem
import com.es.interfazproyectoapp.navegation.AppNavigation
import com.es.interfazproyectoapp.navegation.AppScreen
import com.es.interfazproyectoapp.ui.theme.blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MyAppWithDrawer(
    isDarkMode: Boolean,
    onDarkModeSwitchChange: (Boolean) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,

){

    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)



    // Lista de items que tendra el menu hamburguesa dentro
    val items = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = AppScreen.MainScreen.route
        ),

        NavigationItem(
            title = "Tasks",
            selectedIcon = Icons.Filled.Task,
            unselectedIcon = Icons.Outlined.Task,
            route = AppScreen.TasksScreen.route
        ),

        NavigationItem(
            title = "Calendar",
            selectedIcon = Icons.Filled.CalendarMonth,
            unselectedIcon = Icons.Outlined.CalendarMonth,
            route = AppScreen.CalendarScreen.route
        ),

        NavigationItem(
            title = "User",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = AppScreen.UserScreen.route
        ),

        NavigationItem(
            title = "Create User",
            selectedIcon = Icons.Filled.People,
            unselectedIcon = Icons.Outlined.People,
            route = AppScreen.CreateUserScreen.route
        ),

        NavigationItem(
            title = "Create Task",
            selectedIcon = Icons.Filled.AddTask,
            unselectedIcon = Icons.Outlined.AddTask,
            route = AppScreen.CreateTaskScreen.route
        ),

        NavigationItem(
            title = "About Us",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            route = AppScreen.AboutUsScreen.route
        ),


        NavigationItem(
            title = "Privacy Policy",
            selectedIcon = Icons.Filled.PrivacyTip,
            unselectedIcon = Icons.Outlined.PrivacyTip,
            badgeCount = 1,
            route = AppScreen.PrivacyPolicyScreen.route
        )
    )



    // Obtener la ruta actual
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry.value?.destination?.route

    // Obtener de la lista la parte que corresponde a cada menu
    val bottomBarItems = items.take(4)
    val drawerItems = items.takeLast(4)


    // Controlar la visibilidad de la barra lateral, para no mostrarla en la pantalla inicial ni login
    val isDrawerEnabled = currentRoute != AppScreen.FrontScreen.route &&
            currentRoute != AppScreen.LoginScreen.route &&
            currentRoute != AppScreen.RegisterScreen.route




    if (isDrawerEnabled){ // Controlar si es la pantalla de Welcome o no
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {

                NavigationDrawerContent(
                    items = drawerItems,
                    currentRoute = currentRoute,
                    onItemSelected = {route ->
                        coroutineScope.launch {
                            // Evitar la navegación redundante
                            if (currentRoute != route) {
                                drawerState.close()
                                navController.navigate(route) {
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                    isDarkMode = isDarkMode,
                    onDarkModeSwitchChange = onDarkModeSwitchChange
                )
            }
        ) {

            // Mostrar las barras cuando no es la Welcome Screen
            MyScaffold(
                navController = navController,
                drawerState = drawerState,
                coroutineScope = coroutineScope,
                showBars = true,
                modifier = modifier,
                currentRoute = currentRoute,
                onBottomBarItemSelected = { route ->
                    if (currentRoute != route) {
                        // Evitar la navegación redundante
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                bottomBarItems
            )
        }
    }

    else{
        // No moostrar las barras cuando es la WelcomeScreen
        MyScaffold(
            navController = navController,
            drawerState = null,
            coroutineScope = coroutineScope,
            showBars = false,
            modifier = modifier,
            currentRoute = currentRoute,
            onBottomBarItemSelected = {},
            items
        )
    }

}

/**
 * Scaffold, que permite llamarlo y que se muestre o no, las barras tanto superior como inferior
 */
@Composable
fun MyScaffold(
    navController: NavHostController,
    drawerState: DrawerState?,
    coroutineScope: CoroutineScope,
    showBars: Boolean,
    modifier: Modifier = Modifier,
    currentRoute: String?,
    onBottomBarItemSelected: (String) -> Unit,
    items: List<NavigationItem>
) {
    Scaffold(
        topBar = {
            if (showBars) {
                MyTopAppBar(
                    navController = navController
                ) { coroutineScope.launch { drawerState?.open() } }
            }
        },
        bottomBar = {
            if (showBars) {
                MyBottomAppBar(
                    navController,
                    currentRoute = currentRoute,
                    onItemSelected = onBottomBarItemSelected, // Le pasa la ruta de la pantalla actual
                    listItems = items
                )
            }
        },
        content = { paddingValues ->
            Box(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                AppNavigation(
                    modifier,
                    navController = navController,
                )
            }
        }
    )
}


/**
 * Menu hamburguesa, contenido
 */
@Composable
fun NavigationDrawerContent(
    items: List<NavigationItem>,
    currentRoute: String?,
    onItemSelected: (String) -> Unit,
    isDarkMode: Boolean,
    onDarkModeSwitchChange: (Boolean) -> Unit,

) {

    ModalDrawerSheet {

        Spacer(Modifier.height(16.dp))

        DarkModeSwitch(
            isDarkMode = isDarkMode,
            onDarkModeSwitchChange = onDarkModeSwitchChange,
            // Le da el mismo padding que al resto de las opcs del menu
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        Spacer(Modifier.height(16.dp))

        items.forEach { item ->
            NavigationDrawerItem(
                label = { Text(text = item.title) },
                selected = item.route == currentRoute,
                onClick = { onItemSelected(item.route) },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = if (isDarkMode) Color.Gray else Color.LightGray
                ),
                icon = {
                    Icon(
                        imageVector = if (item.route == currentRoute) {
                            item.selectedIcon
                        }else {
                            item.unselectedIcon
                            item.unselectedIcon
                        },
                        contentDescription = item.title
                    )
                },
                badge = {
                    item.badgeCount?.let {
                        Text(
                            text = item.badgeCount.toString()
                        )
                    }
                },
                modifier = Modifier
                    .padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}


/**
 * Extra añadido. Switch para cambiar entre modo oscuro y claro
 */

@Composable
fun DarkModeSwitch(
    isDarkMode: Boolean,
    onDarkModeSwitchChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxWidth(),

        horizontalAlignment = Alignment.Start
    ){

        Text(
            text = if (isDarkMode) "Light mode" else "Dark mode",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 12.sp
        )

        Switch(
            checked = isDarkMode,
            onCheckedChange = { onDarkModeSwitchChange(it) },
            Modifier
                .align(Alignment.Start)
                .scale(0.8f),
            colors = SwitchDefaults.colors(
                checkedThumbColor = colorResource(R.color.white),
                uncheckedThumbColor = colorResource(R.color.gray),
                checkedTrackColor = blue,
                uncheckedTrackColor = colorResource(R.color.gray2),
            )
        )
    }
}

@Preview
@Composable
fun PreviewSca(

){
}

