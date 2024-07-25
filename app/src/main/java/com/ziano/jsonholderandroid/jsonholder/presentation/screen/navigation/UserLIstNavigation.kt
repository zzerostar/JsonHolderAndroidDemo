package com.ziano.jsonholderandroid.jsonholder.presentation.screen.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/23
 * @desc
 */

const val userListScreenRoute = "UserListScreen"

fun NavGraphBuilder.userListScreen() {
    composable(userListScreenRoute) {
        Text(text = "User")

    }
}
