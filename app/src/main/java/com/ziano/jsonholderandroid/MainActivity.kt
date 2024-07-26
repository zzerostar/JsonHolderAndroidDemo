package com.ziano.jsonholderandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ziano.jsonholderandroid.compose.JsonHolderAndroidNavGraph
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.navigateToJsonHolderGroup
import com.ziano.jsonholderandroid.ui.theme.JsonHolderAndroidTheme
import com.ziano.kotlinandroid.jsonholder.ui.JsonHolderMainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JsonHolderAndroidTheme {
                MyApp()
            }
        }
    }

    companion object {
        fun jump(context: Context) {
            context.startActivity(Intent(context, JsonHolderMainActivity::class.java))
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    JsonHolderAndroidNavGraph(navController)
}

@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                navController.navigateToJsonHolderGroup()
            }) {
                Text("Jetpack Compose")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    context.startActivity(Intent(context, JsonHolderMainActivity::class.java))
                }) {
                Text("Activity + Fragment")
            }

        }

    }
}
