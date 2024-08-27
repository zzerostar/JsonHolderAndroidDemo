package com.ziano.jsonholderandroid.compose.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * @author zz
 * @email zzerostar@163.com
 * @date 2024/5/28
 * @desc
 */
abstract class BaseViewModel<State : IViewState, Intent : IViewIntent>(protected val ioDispatcher: CoroutineDispatcher) : ViewModel() {

    private val initialState: State by lazy { initState() }
    abstract fun initState(): State

    val currentState: State get() = uiState.value

    private val _uiState = MutableStateFlow<State>(initialState)
    val uiState = _uiState.asStateFlow()

    private val _uiIntent = MutableSharedFlow<Intent>()
    val uiIntent = _uiIntent.asSharedFlow()

    protected fun setState(block: State.() -> State) {
        _uiState.update(block)
    }

    protected suspend fun emit(state: State) {
        _uiState.emit(state)
    }

    protected fun sendIntent(intent: Intent) {
        viewModelScope.launch(ioDispatcherContext) {
            _uiIntent.emit(intent)
        }
    }

    abstract fun handleIntent(intent: Intent)


    protected val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        run {
            Log.e(BaseViewModel::class.simpleName, e.toString())
        }
    }

    protected var ioDispatcherContext = exceptionHandler + ioDispatcher
}