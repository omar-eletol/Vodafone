package com.task.cityinput.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.cityinput.domin.usecase.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ChallengesHistoryViewModel @Inject constructor(
    private val getHistoryUseCase: GetHistoryUseCase,
) : ViewModel(
) {

    private val _actions = Channel<ChallengesHistoryActions>()
    val actions = _actions.receiveAsFlow()

    fun emitAction(action: ChallengesHistoryActions) = viewModelScope.launch(Dispatchers.IO) {
        _actions.send(action)
    }


    private val _state = MutableStateFlow<ChallengesHistoryState>(ChallengesHistoryState.Idle)
    val state get() = _state.asStateFlow()
    fun fetchHistoryList() = viewModelScope.launch(Dispatchers.IO) {
        _state.emit(ChallengesHistoryState.Loading)
        _state.emit(
            try {
                val response = getHistoryUseCase.invoke()
                if (response.isEmpty()) ChallengesHistoryState.Empty
                else ChallengesHistoryState.Success(data = response)
            } catch (t: Throwable) {
                ChallengesHistoryState.Error(throwable = t, errorBody = t.message)
            }
        )

    }


}