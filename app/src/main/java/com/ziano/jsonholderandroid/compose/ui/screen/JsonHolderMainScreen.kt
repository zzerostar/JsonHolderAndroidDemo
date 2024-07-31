package com.ziano.jsonholderandroid.compose.ui.screen

import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ziano.jsonholderandroid.compose.theme.Purple500
import com.ziano.jsonholderandroid.compose.ui.JsonHolderNavGraph
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.BottomNavItem

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
        containerColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItem.values().forEachIndexed { index, item ->
            NavigationBarItem(
                label = {
                    Text(text = item.label, fontWeight = FontWeight.Bold)
                },
                icon = {

                    Icon(painter = painterResource(id = item.iconId), contentDescription = "")

                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = Color.Gray, selectedTextColor = Purple500, selectedIconColor = Purple500, unselectedIconColor = Color.Gray, indicatorColor = Color.White
                ),
            )
        }
    }
}