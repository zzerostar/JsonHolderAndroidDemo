package com.ziano.jsonholderandroid.jsonholder.vm

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.base.BaseViewModel
import com.ziano.jsonholderandroid.base.IViewIntent
import com.ziano.jsonholderandroid.base.ViewStatus
import com.ziano.jsonholderandroid.jsonholder.data.model.NetResponse
import com.ziano.jsonholderandroid.jsonholder.data.repositroy.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
class PostDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle, val postRepository: PostRepository) : BaseViewModel<PostDetailViewState, PostDetailIntent>() {

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
                    currentState.copy(status = ViewStatus.error, errorMsg = "")
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

    override fun initState(): PostDetailViewState  = PostDetailViewState(ViewStatus.loading)

    override fun handleIntent(intent: PostDetailIntent) {
    }
}

class PostDetailIntent : IViewIntent