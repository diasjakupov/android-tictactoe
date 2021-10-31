package com.example.tictactoe.data.network

import com.example.tictactoe.data.models.GameCreationParams
import com.example.tictactoe.data.models.GameInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GameApi {

    @GET("game")
    suspend fun getAllAvailableGames(): Response<List<GameInfo>>

    @POST("game/create_game_instance")
    suspend fun createGameInstance(@Body body:GameCreationParams): Response<String>
}