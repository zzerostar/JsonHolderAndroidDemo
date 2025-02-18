package com.ziano.jsonholderandroid.compose.ui.screen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ziano.jsonholderandroid.compose.ui.screen.UserListScreen

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/23
 * @desc
 */

const val userListScreenRoute = "UserListScreen"

fun NavGraphBuilder.userListScreen(backToHome : () -> Unit) {
    composable(userListScreenRoute) {
        UserListScreen(viewModel = hiltViewModel(), backToHome)
    }
}
