package com.ziano.jsonholderandroid.compose.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ziano.jsonholderandroid.compose.base.ListViewStatus
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.compose.vm.PostListViewIntent
import com.ziano.jsonholderandroid.compose.vm.PostListViewModel
import com.ziano.jsonholderandroid.compose.widget.PullToRefreshLazyColumn
import com.ziano.jsonholderandroid.compose.widget.rememberPullToRefreshLazyColumnState

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/6/30
 * @desc
 */
@Composable
fun PostListScreen(viewModel: PostListViewModel, jumpToDetail: (Int) -> Unit) {

    val listViewState = viewModel.uiState.collectAsState().value

    val isRefreshing = listViewState.status == ListViewStatus.refreshing

    val isAppending = listViewState.status == ListViewStatus.appending

    val state = rememberPullToRefreshLazyColumnState(refreshing = isRefreshing,
        onRefresh = { viewModel.handleIntent(PostListViewIntent.Refreshing()) },
        onLoadMore = { viewModel.handleIntent(PostListViewIntent.Appending()) })

    when (listViewState.status) {
        ListViewStatus.error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = listViewState.errorMsg)
        }
        else -> {
            PullToRefreshLazyColumn(state = state, refreshing = isRefreshing, loading = isAppending) {
                items(listViewState.data) { item ->
                    Surface(onClick = {
                        jumpToDetail.invoke(item.id)
                    }) {
                        PostItem(post = item)

                    }
                }
            }

        }
    }

}

@Composable
fun PostItem(post: Post) {
    Column {
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
