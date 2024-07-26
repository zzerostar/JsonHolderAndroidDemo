package com.ziano.jsonholderandroid.compose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziano.jsonholderandroid.compose.base.ViewStatus
import com.ziano.jsonholderandroid.common.data.model.Comment
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.compose.vm.PostDetailViewModel
import com.ziano.jsonholderandroid.compose.widget.CustomFullScreenLoading
import com.ziano.jsonholderandroid.ui.theme.LightGray
import com.ziano.jsonholderandroid.compose.widget.CustomLoading
import kotlin.math.roundToInt

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/22
 * @desc
 */
@Composable
fun PostDetailScreen(postDetailViewModel: PostDetailViewModel) {

    val state = postDetailViewModel.uiState.collectAsState().value

    Scaffold {
        when (state.status) {
            ViewStatus.loading -> CustomFullScreenLoading()

            ViewStatus.success -> Content(state.detail!!, state.comments)
            ViewStatus.error -> Text("error")
        }
    }
}

@Composable
fun Content(post: Post, comments: List<Comment> = mutableListOf()) {
    val toolbarHeight = 148.dp
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }

    val offset = remember {
        mutableStateOf(0f)
    }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                var newOffset = (offset.value) + (0.5f) * available.y
                offset.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
    }
    Box(
        Modifier
            .fillMaxSize()
            // attach as a parent to the nested scroll system
            .nestedScroll(nestedScrollConnection)
    ) {

        Header(
            post,
            modifier = Modifier
                .height(toolbarHeight)
                .offset { IntOffset(x = 0, y = offset.value.roundToInt()) },
        )


        // our list with build in nested scroll support that will notify us about its scroll
        LazyColumn(contentPadding = PaddingValues(top = toolbarHeight)) {
            items(comments.size) { index ->
                val comment = comments[index]
                Column {
                    Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                        Text(text = "ID:${comment.id}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(3.dp))
                        Text(text = comment.email, fontSize = 13.sp, color = LightGray, lineHeight = 16.sp)
                        Spacer(Modifier.height(10.dp))
                        Text(text = comment.body, fontSize = 13.sp, lineHeight = 16.sp)
                    }
                    Divider(color = Color.LightGray)
                }
            }
        }

    }
}

@Composable
fun Header(post: Post, modifier: Modifier) {
    Column(modifier = modifier.background(Color.Blue)) {
        Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(text = "ID:${post.id}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(10.dp))
            Text(text = post.title, fontSize = 13.sp, color = Color.Gray, lineHeight = 16.sp)
            Spacer(Modifier.height(10.dp))
            Text(text = post.content, fontSize = 13.sp, lineHeight = 16.sp)
        }
        Divider(color = Color.LightGray)
    }
}