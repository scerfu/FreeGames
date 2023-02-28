package com.example.freetogames.rest

import com.example.freetogames.model.Games
import com.example.freetogames.viewmodel.utils.ENDPOINT_GAMES
import retrofit2.Response
import retrofit2.http.GET

interface GamesAPI {

    @GET(ENDPOINT_GAMES)
    suspend fun getAllGames(): Response<Games>
}