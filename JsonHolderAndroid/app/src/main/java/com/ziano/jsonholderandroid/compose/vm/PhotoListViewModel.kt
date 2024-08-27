package com.ziano.jsonholderandroid.compose.vm

import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.common.data.model.Photo
import com.ziano.jsonholderandroid.common.data.repositroy.PhotoRepository
import com.ziano.jsonholderandroid.compose.base.BaseViewModel
import com.ziano.jsonholderandroid.compose.base.CommonViewState
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
 * @date 2024/6/25
 * @desc
 */

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    val photoRepository: PhotoRepository,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel<PhotoListViewState, PhotoViewIntent>
    (ioDispatcher) {
    init {
        getPhotos()
    }

    fun getPhotos() {
        viewModelScope.launch(ioDispatcherContext) {
            photoRepository.getPhotoList(1).map { response ->
                currentState.fromNetResponse(response)
            }.collect {
                setState { it }
            }
        }
    }

    override fun initState(): PhotoListViewState = PhotoListViewState()

    override fun handleIntent(intent: PhotoViewIntent) {
    }
}

class PhotoListViewState(
    override val status: ViewStatus = ViewStatus.loading,
    override val data: List<Photo>? = null,
    override val errorMsg: String = ""
) : CommonViewState<List<Photo>, PhotoListViewState>() {

    override fun copyImpl(status: ViewStatus, data: List<Photo>?, errorMsg: String): PhotoListViewState {
        return PhotoListViewState(status, data, errorMsg)
    }
}

class PhotoViewIntent : IViewIntent