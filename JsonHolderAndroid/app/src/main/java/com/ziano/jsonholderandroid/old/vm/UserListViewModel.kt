package com.ziano.jsonholderandroid.old.vm

import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.common.data.model.User
import com.ziano.jsonholderandroid.common.data.repositroy.UserRepository
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
 * @date 2024/7/16
 * @desc
 */
@HiltViewModel
class UserListViewModel @Inject constructor(val userRepository: UserRepository) : BaseViewModel() {

    private val _users: MutableStateFlow<State<List<User>>> = MutableStateFlow(State.loading())

    val users = _users.asStateFlow()

    fun getUserList() {
        viewModelScope.launch(ioDispatcherContext) {
            userRepository.getUser().map { State.fromResponse(it) }.collect {
                _users.emit(it)
            }
        }
    }
}