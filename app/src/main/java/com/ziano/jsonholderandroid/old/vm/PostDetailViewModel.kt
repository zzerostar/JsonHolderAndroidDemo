package com.ziano.jsonholderandroid.old.vm

import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.common.data.model.Comment
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.common.data.repositroy.PostRepository
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
class PostDetailViewModel @Inject constructor(val postRepository: PostRepository) : BaseViewModel() {

    private val _postDetail: MutableStateFlow<State<Post>> = MutableStateFlow(State.loading())

    val postDetail = _postDetail.asStateFlow()

    private val _comments: MutableStateFlow<State<List<Comment>>> = MutableStateFlow(State.loading())

    val comments = _comments.asStateFlow()

    fun getPostDetail(id: Int) {
        viewModelScope.launch(ioDispatcherContext) {
            postRepository.getPostDetail(id).map {
                State.fromResponse(it)
            }.collect {
                _postDetail.emit(it)
            }
        }
    }

    fun getPostComments(postId: Int) {
        viewModelScope.launch(ioDispatcherContext) {
            postRepository.getComments(postId).map {
                State.fromResponse(it)
            }.collect {
                _comments.emit(it)
            }
        }
    }


}