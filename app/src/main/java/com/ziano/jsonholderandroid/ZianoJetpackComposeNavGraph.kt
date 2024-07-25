package com.ziano.jsonholderandroid

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ziano.jsonholderandroid.jsonholder.presentation.screen.navigation.jsonHolderGroup
import com.ziano.jsonholderandroid.jsonholder.presentation.screen.navigation.jsonHolderMainScreen
import com.ziano.jsonholderandroid.jsonholder.presentation.screen.navigation.postDetailScreen

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