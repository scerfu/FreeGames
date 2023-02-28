package com.example.freetogames.viewmodel.utils

import com.example.freetogames.model.Games
import com.example.freetogames.model.GamesItem

sealed class UIState {
    object LOADING : UIState()
    //object EMPTY : UIState()
    class FAILURE(val errorMessage: String) : UIState()
    data class SUCCESS(val response: Games) : UIState()
}

//sealed class ResponseType<out T>{
//    object LOADING : ResponseType<Nothing>()
//    data class SUCCESS<T>(val response: T): ResponseType<T>()
//    class ERROR(val e: String): ResponseType<Nothing>()
//}
