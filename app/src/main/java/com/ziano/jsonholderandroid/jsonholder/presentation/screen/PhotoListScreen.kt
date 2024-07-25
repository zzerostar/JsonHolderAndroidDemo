package com.ziano.jsonholderandroid.jsonholder.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.ziano.jsonholderandroid.base.ViewStatus
import com.ziano.jsonholderandroid.jsonholder.vm.PhotoListViewModel
import com.ziano.jsonholderandroid.widget.CustomLoading

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/11
 * @desc
 */
@Composable
fun PhotoListScreen(viewModel: PhotoListViewModel) {

    val state = viewModel.uiState.collectAsState().value

    val lazyGridState = rememberLazyGridState()

    when (state.status) {
        ViewStatus.loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CustomLoading(Modifier.align(Alignment.Center), size = 50.dp)
            }
        }

        ViewStatus.success -> LazyVerticalGrid(columns = GridCells.Fixed(2), state = lazyGridState) {
            itemsIndexed(state.data!!) { index, item ->

                val screenWidth = LocalConfiguration.current.screenWidthDp
                val blockImageSize = (screenWidth / 2).dp

                SubcomposeAsyncImage(model = item.url, contentDescription = null, loading = {
                    Box(modifier = Modifier.size(blockImageSize, blockImageSize), contentAlignment = Alignment.Center) {
                        CustomLoading(Modifier.size(30.dp, 30.dp))
                    }
                })
            }
        }

        ViewStatus.error -> Text("error")

    }

}