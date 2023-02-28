package com.example.freetogames.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freetogames.model.GamesItem
import com.example.freetogames.rest.Repository
import com.example.freetogames.viewmodel.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "GamesViewModel"

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        Log.d(TAG, "Initialize viewmodel: init called")
        getAllGames()
    }

    var selectedGame: GamesItem? = null

    private val _data: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val data: LiveData<UIState> get() = _data

    private fun getAllGames() {
        Log.d(TAG, "getAllGames: getAllGames: first call to games")
        viewModelScope.launch {
            repository.getAllGames().collect {
                _data.postValue(it)
            }
        }
    }
}