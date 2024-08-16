package com.ziano.jsonholderandroid.compose.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.navigateToPostDetail
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.photoListScreen
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.postListScreen
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.postListScreenRoute
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.userListScreen

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/22
 * @desc
 */
@Composable
fun JsonHolderNavGraph(bottomNavController: NavHostController, navController: NavController, modifier: Modifier) {

    NavHost(navController = bottomNavController, startDestination = postListScreenRoute, modifier) {
        postListScreen() {
            navController.navigateToPostDetail(it)
        }
        photoListScreen({
            navController.popBackStack()
        })
        userListScreen({
            navController.popBackStack()

        })
    }
}