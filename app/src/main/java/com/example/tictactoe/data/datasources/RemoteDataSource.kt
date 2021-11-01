package com.example.tictactoe.data.datasources

import com.example.tictactoe.data.models.*
import retrofit2.Response

interface RemoteDataSource {

    suspend fun login(loginBody:LoginBody): Response<AuthResponse.LoginResponse>
    suspend fun gameList():Response<List<GameInfo>>
    suspend fun createGameInstance(body: GameCreationParams): Response<String>
    suspend fun register(registerBody:RegisterBody): Response<AuthResponse.RegisterResponse>
}