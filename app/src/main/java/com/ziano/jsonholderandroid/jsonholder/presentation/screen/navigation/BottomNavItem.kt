package com.ziano.jsonholderandroid.jsonholder.presentation.screen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/28
 * @desc
 */
sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Post : BottomNavItem(postListScreenRoute, Icons.Default.Email, "Post")
    object Photo : BottomNavItem(photoListScreenRoute, Icons.Default.Face, "Photo")
    object User : BottomNavItem(userListScreenRoute, Icons.Default.Person, "User")

    companion object {
        fun values(): List<BottomNavItem> {
            return listOf(Post, Photo, User)
        }
    }
}