package com.ziano.jsonholderandroid.jsonholder.vm

import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.base.BaseViewModel
import com.ziano.jsonholderandroid.base.CommonViewState
import com.ziano.jsonholderandroid.base.IViewIntent
import com.ziano.jsonholderandroid.base.ViewStatus
import com.ziano.jsonholderandroid.jsonholder.data.model.Photo
import com.ziano.jsonholderandroid.jsonholder.data.repositroy.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
class PhotoListViewModel @Inject constructor(val photoRepository: PhotoRepository) : BaseViewModel<PhotoListViewState, PhotoViewIntent>() {
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