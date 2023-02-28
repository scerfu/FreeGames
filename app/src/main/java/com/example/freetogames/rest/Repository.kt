package com.example.freetogames.rest

import com.example.freetogames.viewmodel.utils.UIState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

interface Repository {
    //val game: StateFlow<UIState>
    suspend fun getAllGames(): Flow<UIState>
}

class GamesRepository @Inject constructor(
    private val gamesAPI: GamesAPI
) : Repository {

//    private val _game = MutableStateFlow<UIState>(UIState.EMPTY)
//    override val game: StateFlow<UIState> get() = _game

    override suspend fun getAllGames(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        try {
            val response = gamesAPI.getAllGames()
            if (response.isSuccessful) {
                response.body()?.let {
                    //_game.value = UIState.SUCCESS(it)
                    emit(UIState.SUCCESS(it))
                } ?: throw Exception("Games response null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
           // _game.value = UIState.FAILURE(e.message.toString())
            emit(UIState.FAILURE(e.message.toString()))
        }
    }
}