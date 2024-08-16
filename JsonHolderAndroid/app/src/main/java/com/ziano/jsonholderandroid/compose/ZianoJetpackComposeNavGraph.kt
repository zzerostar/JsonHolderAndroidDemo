package com.ziano.jsonholderandroid.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ziano.jsonholderandroid.HomeScreen
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.jsonHolderGroup
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.jsonHolderMainScreen
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.postDetailScreen

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/11
 * @desc
 */

@Composable
fun JsonHolderAndroidNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = homeScreenRoute) {
        composable(route = homeScreenRoute) {
            HomeScreen(navController)
        }

        jsonHolderGroup {
            jsonHolderMainScreen(navController)
            postDetailScreen()
        }

    }
}