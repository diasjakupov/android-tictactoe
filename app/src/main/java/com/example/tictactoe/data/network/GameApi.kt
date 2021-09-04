package com.example.tictactoe.data.network

import com.example.tictactoe.data.models.GameInfo
import retrofit2.Response
import retrofit2.http.GET

interface GameApi {

    @GET("game")
    suspend fun getAllAvailableGames(): Response<List<GameInfo>>
}