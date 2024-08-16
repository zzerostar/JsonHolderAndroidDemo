package com.ziano.jsonholderandroid.compose.ui.screen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ziano.jsonholderandroid.compose.ui.screen.PostDetailScreen

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/23
 * @desc
 */

const val postDetailScreenRoute = "PostListScreen"

fun NavController.navigateToPostDetail(postId: Int) {
    this.navigate(postDetailScreenRoute.plus("/${postId}"))
}

fun NavGraphBuilder.postDetailScreen() {
    composable(postListScreenRoute.plus("/{postId}"), arguments = listOf(navArgument("postId") {
        type = NavType.IntType
    })) {
        PostDetailScreen(hiltViewModel())
    }
}