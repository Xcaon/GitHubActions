package com.example.flowprueba

import android.util.Log
import androidx.compose.runtime.saveable.mapSaver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowprueba.data.SuscribeteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val suscribeteRepository = SuscribeteRepository()
    private val _uiState = MutableStateFlow<MainUIState>(MainUIState.Loading)
    val uiState :  StateFlow<MainUIState> = _uiState

    fun example() {
        viewModelScope.launch(Dispatchers.IO) {
            suscribeteRepository.counter.collect() {
                Log.i("fer", it.toString())
            }
        }

    }

    fun example2() {
        viewModelScope.launch {
            suscribeteRepository.counter
                .map { it.toString() }
                .onEach { save(it) }
                .catch { error ->
                    Log.i("aristiCurso", "Error: ${error.message}")
                }
                .collect { bombitas ->
                    Log.i("fer", bombitas)
                }
        }
    }

    fun save(a: String) {
        Log.i("fer", a)
    }

}