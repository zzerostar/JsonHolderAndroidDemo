package com.ziano.jsonholderandroid

import android.os.Bundle
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ziano.jsonholderandroid.compose.JsonHolderAndroidNavGraph
import com.ziano.jsonholderandroid.compose.ui.screen.navigation.navigateToJsonHolderGroup
import com.ziano.jsonholderandroid.ui.theme.JsonHolderAndroidTheme
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
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    JsonHolderAndroidNavGraph(navController)
}

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Button(onClick = {
                navController.navigateToJsonHolderGroup()
            }) {
                Text("JsonHolderMain")
            }

            Text(
                "Todo: " +
                        "1.NetRequest, add logic to handle whether the reqeust use cache \n" +
                        "2.PostDetail appbar scroll animation"
            )
        }

    }
}
