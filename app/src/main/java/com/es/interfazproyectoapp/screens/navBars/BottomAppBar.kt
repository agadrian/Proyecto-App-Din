package com.es.interfazproyectoapp.screens.navBars

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.es.interfazproyectoapp.model.NavigationItem
import com.es.interfazproyectoapp.ui.theme.white


@Composable
fun MyBottomAppBar(
    navController: NavHostController,
    currentRoute: String?,
    onItemSelected: (String) -> Unit,
    listItems: List<NavigationItem>
) {


    Column {

        HorizontalDivider(
            color = white,
            thickness = 0.1.dp,
            modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
        )

        NavigationBar(
            containerColor = Color.Black
        ) {
            listItems.forEach { item ->
                val isSelected = item.route == currentRoute

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            onItemSelected(item.route)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Black,
                        selectedTextColor = Color.White,
                        unselectedTextColor = Color.Gray,
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray
                    ),

                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title
                        )
                    }
                )
            }
        }
    }
}


