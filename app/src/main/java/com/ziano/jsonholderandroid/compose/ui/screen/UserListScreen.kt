package com.ziano.jsonholderandroid.compose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziano.jsonholderandroid.R
import com.ziano.jsonholderandroid.common.data.model.User
import com.ziano.jsonholderandroid.compose.base.ViewStatus
import com.ziano.jsonholderandroid.compose.vm.UserListViewModel
import com.ziano.jsonholderandroid.compose.widget.CustomFullScreenLoading
import com.ziano.jsonholderandroid.ui.theme.Colors

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/26
 * @desc
 */
@Composable
fun UserListScreen(viewModel: UserListViewModel) {

    val state = viewModel.uiState.collectAsState().value

    when (state.status) {
        ViewStatus.loading -> CustomFullScreenLoading()
        ViewStatus.success -> LazyColumn {
            itemsIndexed(state.data!!) { index, item ->
                Item(user = item)
            }
        }

        ViewStatus.error -> Text("error")
    }
}

@Composable
fun Item(user: User) {
    Column {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Row {
                Icon(painter = painterResource(id = R.drawable.icon_name), contentDescription = "name")
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = user.name, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(3.dp))
            Row {
                Icon(painter = painterResource(id = R.drawable.icon_email), contentDescription = "email")
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = user.email, fontSize = 16.sp, color = Colors.Black45)
            }
            Spacer(Modifier.height(3.dp))
            Row {
                Icon(painter = painterResource(id = R.drawable.icon_phone), contentDescription = "phone")
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = user.phone, fontSize = 16.sp, color = Colors.Black45)
            }
            Spacer(Modifier.height(3.dp))
            Row {
                Icon(painter = painterResource(id = R.drawable.icon_address), contentDescription = "address")
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = "${user.address.street} ${user.address.suite} ${user.address.city} ", fontSize = 16.sp, color = Colors.Black45)
            }
            Spacer(Modifier.height(3.dp))
            Row {
                Icon(painter = painterResource(id = R.drawable.icon_company), contentDescription = "company")
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = user.company.name, fontSize = 16.sp, color = Colors.Black45)
            }

        }
        Divider(color = Color.LightGray)

    }
}