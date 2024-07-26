package com.ziano.jsonholderandroid.compose.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.BottomNavItem
import com.ziano.jsonholderandroid.compose.ui.JsonHolderNavGraph

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/11
 * @desc
 */
@Composable
fun JsonHolderMainScreen(navController: NavHostController) {

    val bottomNavController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        JsonHolderMainBottomBar(navController = bottomNavController)
    }) { innerPadding ->

        JsonHolderNavGraph(bottomNavController, navController, modifier = Modifier.padding(innerPadding))
    }
}

//If use position, we cannot encapsulate the BottomBar, because the position parameter cannot change
@Composable
fun JsonHolderMainBottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItem.values().forEachIndexed { index, screen ->
            NavigationBarItem(
                label = {
                    Text(text = screen.label)
                },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray, selectedTextColor = Color.Black, selectedIconColor = Color.Black, unselectedIconColor = Color.Black, indicatorColor = Color.White
                ),
            )
        }
    }
}