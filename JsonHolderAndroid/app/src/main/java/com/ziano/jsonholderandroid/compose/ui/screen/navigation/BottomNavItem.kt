package com.ziano.jsonholderandroid.compose.ui.screen.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.ziano.jsonholderandroid.R

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/28
 * @desc
 */
sealed class BottomNavItem(val route: String, val iconId: Int, val label: String) {
    object Post : BottomNavItem(postListScreenRoute, R.drawable.nav_icon_post, "Post")
    object Photo : BottomNavItem(photoListScreenRoute, R.drawable.nav_icon_photo, "Photo")
    object User : BottomNavItem(userListScreenRoute, R.drawable.nav_icon_user, "User")

    companion object {
        fun values(): List<BottomNavItem> {
            return listOf(Post, Photo, User)
        }
    }
}