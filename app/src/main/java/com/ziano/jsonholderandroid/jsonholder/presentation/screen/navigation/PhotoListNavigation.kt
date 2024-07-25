package com.ziano.jsonholderandroid.jsonholder.presentation.screen.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ziano.jsonholderandroid.jsonholder.presentation.screen.PhotoListScreen

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/23
 * @desc
 */

const val photoListScreenRoute = "PhotoListScreen"

fun NavGraphBuilder.photoListScreen() {
    composable(photoListScreenRoute) {
        PhotoListScreen(viewModel = hiltViewModel())
    }
}