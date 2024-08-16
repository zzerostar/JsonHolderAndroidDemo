package com.ziano.jsonholderandroid.old.vm

import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.common.data.model.Post
import com.ziano.jsonholderandroid.common.data.repositroy.PostRepository
import com.ziano.jsonholderandroid.jsonholder.old.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/23
 * @desc
 */
@HiltViewModel
class PostListViewModel @Inject constructor(val postRepository: PostRepository) : BaseViewModel() {

    var _postlist: MutableStateFlow<ListViewState<List<Post>>> = MutableStateFlow(ListViewState.loading())

    val postlist: StateFlow<ListViewState<List<Post>>>
        get() = _postlist

    var _startIndex = 0;
    fun refresh() {
        _startIndex = 0;
        getPostList()
    }

    fun loadMore() {
        getPostList()
    }
    fun getPostList() {
        viewModelScope.launch(ioDispatcherContext) {
            postRepository.getPostList(_startIndex).map { response -> ListViewState.fromResponse(response, loadMore = _startIndex > 0) }.collect {
                if (it.isSuccess() || it.isLoadMore()) {
                    _startIndex += 20
                }
                _postlist.emit(it)
            }
        }
    }
}