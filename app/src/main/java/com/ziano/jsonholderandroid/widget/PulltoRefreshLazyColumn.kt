package com.ziano.jsonholderandroid.widget

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshDefaults
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/8
 * @desc
 */


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshLazyColumn(
    modifier: Modifier = Modifier,
    state: PullToRefreshLazyColumnState,
    refreshing: Boolean,
    loading: Boolean,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    loadingContent: (@Composable () -> Unit)? = null,
    content: LazyListScope.() -> Unit,
) {
    val lazyListState = state.lazyColumnState;
    val listLayoutInfo by remember {
        derivedStateOf { lazyListState.layoutInfo }
    }

    Box(
        modifier = modifier
            .pullRefresh(state.pullToRefreshState)
            .fillMaxWidth()
    ) {
        LazyColumn(contentPadding = contentPadding,
            state = state.lazyColumnState,
            reverseLayout = reverseLayout,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
            flingBehavior = flingBehavior,
            userScrollEnabled = userScrollEnabled,
            content = {
                content()
                item {
                    if (loadingContent != null) {
                        loadingContent()
                    } else {
                        if (loading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                            ) {

                                Row(Modifier.align(Alignment.Center)) {

                                    CustomLoading(Modifier
                                        .size(20.dp)
                                        .align(Alignment.CenterVertically), size = 25.dp)
                                    Spacer(modifier = Modifier.width(50.dp))
                                    Text("Loading", fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterVertically))
                                }

                            }
                        }
                    }
                }
            })

        PullRefreshIndicator(refreshing = refreshing, state = state.pullToRefreshState, Modifier.align(Alignment.TopCenter))
    }

    var lastTimeIsScrollInProgress by remember {
        mutableStateOf(lazyListState.isScrollInProgress)
    }

    var lastTimeVisibleIndex by remember {
        mutableStateOf(listLayoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0)
    }

    val currentIsScrollInProgress = lazyListState.isScrollInProgress
    val currentLastVisibleIndex = listLayoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

    if (!currentIsScrollInProgress && lastTimeIsScrollInProgress) {
        if (currentLastVisibleIndex != lastTimeVisibleIndex) {
            val isScrollDown = currentLastVisibleIndex > lastTimeVisibleIndex
            val remainCount = listLayoutInfo.totalItemsCount - currentLastVisibleIndex - 1
            if (isScrollDown && remainCount <= state.loadMoreState.loadMoreRemainCountThreshold) {
                LaunchedEffect(Unit) {
                    state.loadMoreState.onLoadMore()
                }
            }
        }
        lastTimeVisibleIndex = currentLastVisibleIndex
    }

    lastTimeIsScrollInProgress = currentIsScrollInProgress

}

fun rememberLoadMoreState(
    loadMoreRemainCountThreshold: Int,
    onLoadMore: () -> Unit,
): LoadMoreState {
    return LoadMoreState(loadMoreRemainCountThreshold, onLoadMore);
}

data class LoadMoreState(val loadMoreRemainCountThreshold: Int, val onLoadMore: () -> Unit);

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberPullToRefreshLazyColumnState(
    refreshing: Boolean,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    refreshThreshold: Dp = PullRefreshDefaults.RefreshThreshold,
    refreshingOffset: Dp = PullRefreshDefaults.RefreshingOffset,
    loadMoreRemainCountThreshold: Int = 1,
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0,
): PullToRefreshLazyColumnState {
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = refreshing, onRefresh = onRefresh, refreshingOffset = refreshingOffset, refreshThreshold = refreshThreshold
    )

    val lazyColumnState = rememberLazyListState(
        initialFirstVisibleItemIndex = initialFirstVisibleItemIndex, initialFirstVisibleItemScrollOffset = initialFirstVisibleItemScrollOffset
    )

    val loadMoreState = rememberLoadMoreState(
        loadMoreRemainCountThreshold = loadMoreRemainCountThreshold, onLoadMore = onLoadMore
    )

    return remember {
        PullToRefreshLazyColumnState(
            lazyColumnState = lazyColumnState, pullToRefreshState = pullToRefreshState, loadMoreState = loadMoreState
        );
    }
}

data class PullToRefreshLazyColumnState @OptIn(ExperimentalMaterialApi::class) constructor(
    val lazyColumnState: LazyListState, val pullToRefreshState: PullRefreshState, val loadMoreState: LoadMoreState
)