package com.ziano.jsonholderandroid.compose.vm

import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.common.data.repositroy.PostRepository
import com.ziano.jsonholderandroid.compose.base.BaseViewModel
import com.ziano.jsonholderandroid.compose.base.CommonListViewState
import com.ziano.jsonholderandroid.compose.base.IViewIntent
import com.ziano.jsonholderandroid.compose.base.ListViewStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/9
 * @desc
 */
@HiltViewModel
class PostListViewModel @Inject constructor(
    val postRepository: PostRepository,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel<PostListViewState, PostListViewIntent>(ioDispatcher) {

    override fun initState(): PostListViewState = PostListViewState(ListViewStatus.refreshing)

    override fun handleIntent(intent: PostListViewIntent) {

        when (intent) {
            is PostListViewIntent.Refreshing -> refresh()
            is PostListViewIntent.Appending -> loadMore()
        }
    }

    private var _startIndex = 0;

    init {
        refresh()
    }

    private fun refresh() {
        _startIndex = 0
        getPostList()
    }

    private fun loadMore() {
        getPostList()
    }

    fun getPostList() {
        val isAppending = _startIndex > 0

        if (isAppending) {
            setState {
                currentState.copy(ListViewStatus.appending)
            }
        } else {
            setState {
                currentState.copy(ListViewStatus.refreshing)
            }
        }
        viewModelScope.launch(ioDispatcherContext) {
            postRepository.getPostList(_startIndex).map {
                currentState.fromNetResponse(it, isAppending)
            }.collect {
                if (it.status == ListViewStatus.success) {
                    _startIndex += 20
                }
                setState { it }
            }
        }
    }

}

class PostListViewState(
    override val status: ListViewStatus = ListViewStatus.refreshing,
    override val data: List<Post> = listOf(),
    override val errorMsg: String = ""
) : CommonListViewState<Post, PostListViewState>() {

    override fun copyImpl(status: ListViewStatus, data: List<Post>, errorMsg: String): PostListViewState {
        return PostListViewState(status, data = data, errorMsg = errorMsg)
    }

}

sealed class PostListViewIntent : IViewIntent {
    class Refreshing : PostListViewIntent()

    class Appending : PostListViewIntent()


}