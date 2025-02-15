package com.task.forecastlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.forecastlist.domain.usecase.GetDaysForeCastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ForeCastViewModel @Inject constructor(
    private val getDaysForeCastUseCase: GetDaysForeCastUseCase,
) : ViewModel(
) {

    private val _actions = Channel<ForeCastActions>()
    val actions = _actions.receiveAsFlow()

    fun emitAction(action: ForeCastActions) = viewModelScope.launch(Dispatchers.IO) {
        _actions.send(action)
    }


    private val _getForeCastListState =
        MutableStateFlow<ForeCastListState>(ForeCastListState.Idle)
    val getForeCastListState get() = _getForeCastListState.asStateFlow()

    fun getForeCastList(lat: String, lon: String) = viewModelScope.launch(Dispatchers.IO) {
        _getForeCastListState.emit(ForeCastListState.Loading)
        _getForeCastListState.emit(
            try {
                val response = getDaysForeCastUseCase.invoke(lat = lat, lon = lon)
                if (response?.list.isNullOrEmpty()) ForeCastListState.Empty
                else ForeCastListState.Success(data = response)
            } catch (t: Throwable) {
                ForeCastListState.Error(throwable = t, errorBody = t.message)
            }
        )

    }


}