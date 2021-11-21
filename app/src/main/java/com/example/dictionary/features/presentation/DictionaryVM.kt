package com.example.dictionary.features.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.features.domain.use_case.GetWordDetails
import com.example.dictionary.handler.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryVM @Inject constructor(
    private val getWordDetails: GetWordDetails
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(DictionaryState())
    val state: State<DictionaryState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job?= null

    fun onSearch(query: String){
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordDetails(query).onEach { response ->
                when(response){
                    is Response.Success -> {
                        _state.value = state.value.copy(
                            wordDetailsItem = response.data?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Response.Error -> {
                        _state.value = state.value.copy(
                            wordDetailsItem = response.data?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UIEvent.ShowSnackbar(response.message?: "Unknown error"))
                    }

                    is Response.Loading -> {
                        _state.value = state.value.copy(
                            wordDetailsItem = response.data?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent{
        data class ShowSnackbar(val message: String): UIEvent()
    }

}