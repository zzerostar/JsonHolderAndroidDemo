package com.ziano.jsonholderandroid.old.vm

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.common.data.model.Photo
import com.ziano.jsonholderandroid.common.data.repositroy.PhotoRepository
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
 * @date 2024/6/25
 * @desc
 */
@HiltViewModel
class PhotoListViewModel @Inject constructor(val photoRepository : PhotoRepository) : BaseViewModel() {

    private val _photos: MutableStateFlow<State<List<Photo>>> = MutableStateFlow<State<List<Photo>>>(State.loading())

    val photos = _photos.asStateFlow()

    fun getPhotos() {
        viewModelScope.launch(ioDispatcherContext)  {
            _photos.emit(State.loading())
            photoRepository.getPhotoList(1).map { response -> State.fromResponse(response) }
                .collect {
                    _photos.emit(it)
                }
        }
    }


}