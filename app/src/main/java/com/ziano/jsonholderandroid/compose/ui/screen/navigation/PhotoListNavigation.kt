package com.ziano.jsonholderandroid.compose.ui.screen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ziano.jsonholderandroid.compose.ui.screen.PhotoListScreen

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/23
 * @desc
 */

const val photoListScreenRoute = "PhotoListScreen"

fun NavGraphBuilder.photoListScreen(backToHome : () -> Unit) {
    composable(photoListScreenRoute) {
        PhotoListScreen(viewModel = hiltViewModel(), backToHome)
    }
}