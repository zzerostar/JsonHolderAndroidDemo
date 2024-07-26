package com.ziano.jsonholderandroid.compose.ui.screen.navigation

import androidx.compose.material3.Text
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

fun NavGraphBuilder.userListScreen() {
    composable(userListScreenRoute) {
        UserListScreen(viewModel = hiltViewModel())
    }
}
