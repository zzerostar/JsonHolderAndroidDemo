package com.ziano.jsonholderandroid.compose.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.common.data.model.NetResponse
import com.ziano.jsonholderandroid.common.data.repositroy.PostRepository
import com.ziano.jsonholderandroid.compose.base.BaseViewModel
import com.ziano.jsonholderandroid.compose.base.IViewIntent
import com.ziano.jsonholderandroid.compose.base.ViewStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/26
 * @desc
 */
@HiltViewModel
class PostDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle, val postRepository: PostRepository, ioDispatcher: CoroutineDispatcher) :
    BaseViewModel<PostDetailViewState, PostDetailIntent>(ioDispatcher) {

    init {
        val postId = savedStateHandle.get<Int>("postId") ?: 0
        getPostDetail(postId)

    }

    fun getPostDetail(id: Int) {
        viewModelScope.launch(ioDispatcherContext) {
            postRepository.getPostDetail(id).map { response ->
                if (response is NetResponse.Success) {
                    getPostComments(id)
                    currentState.copy(status = ViewStatus.success, detail = response.data)
                } else {
                    currentState.copy(status = ViewStatus.error, errorMsg = (response as NetResponse.Failed).errorMsg)
                }

            }.collect {
                setState { it }
            }
        }
    }

    fun getPostComments(postId: Int) {
        viewModelScope.launch(ioDispatcherContext) {
            postRepository.getComments(postId).map { response ->
                if (response is NetResponse.Success) {
                    currentState.copy(status = ViewStatus.success, comments = response.data)
                } else {
                    currentState
                }
            }.collect {
                setState { it }
            }
        }
    }

    override fun initState(): PostDetailViewState = PostDetailViewState(ViewStatus.loading)

    override fun handleIntent(intent: PostDetailIntent) {
    }
}

class PostDetailIntent : IViewIntent