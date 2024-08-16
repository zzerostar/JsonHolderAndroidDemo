package com.ziano.jsonholderandroid.compose.ui.screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ziano.jsonholderandroid.compose.ui.screen.JsonHolderMainScreen

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/23
 * @desc
 */
const val jsonHolderMainScreenRoute = "JsonHolderMainScreen"

const val jsonHolderMainGroup = "JsonHolderGroup"

fun NavController.navigateToJsonHolderGroup() {
    this.navigate(jsonHolderMainGroup)
}

fun NavGraphBuilder.jsonHolderGroup(builder: NavGraphBuilder.() -> Unit) {
    this.navigation(route = jsonHolderMainGroup, startDestination = jsonHolderMainScreenRoute) {
        builder()
    }
}

fun NavGraphBuilder.jsonHolderMainScreen(navController: NavHostController) {
    composable(jsonHolderMainScreenRoute) {
        JsonHolderMainScreen(navController = navController)
    }
}