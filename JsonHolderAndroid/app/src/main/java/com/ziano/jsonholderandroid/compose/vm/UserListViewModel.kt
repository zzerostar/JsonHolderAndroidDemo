package com.ziano.jsonholderandroid.compose.vm

import androidx.lifecycle.viewModelScope
import com.ziano.jsonholderandroid.common.data.model.User
import com.ziano.jsonholderandroid.common.data.repositroy.UserRepository
import com.ziano.jsonholderandroid.compose.base.BaseViewModel
import com.ziano.jsonholderandroid.compose.base.CommonViewState
import com.ziano.jsonholderandroid.compose.base.IViewIntent
import com.ziano.jsonholderandroid.compose.base.ViewStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/7/26
 * @desc
 */
@HiltViewModel
class UserListViewModel @Inject constructor(val userRepository: UserRepository) : BaseViewModel<UserListViewState, UserListViewIntent>() {
    override fun initState(): UserListViewState = UserListViewState()

    override fun handleIntent(intent: UserListViewIntent) {
    }

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch(ioDispatcherContext) {
            userRepository.getUser().map {
                currentState.fromNetResponse(it)
            }.collect {
                setState { it }
            }
        }
    }

}

class UserListViewState(
    override val status: ViewStatus = ViewStatus.loading, override val data: List<User>? = listOf(), override val errorMsg: String = ""
) : CommonViewState<List<User>, UserListViewState>() {
    override fun copyImpl(status: ViewStatus, data: List<User>?, errorMsg: String): UserListViewState {
        return UserListViewState(status = status, data = data, errorMsg = errorMsg)
    }
}

class UserListViewIntent : IViewIntent