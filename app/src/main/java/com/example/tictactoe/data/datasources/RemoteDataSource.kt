package com.example.tictactoe.data.datasources

import com.example.tictactoe.data.models.GameInfo
import com.example.tictactoe.data.models.LoginBody
import com.example.tictactoe.data.models.LoginResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun login(loginBody:LoginBody): Response<LoginResponse>
    suspend fun gameList():Response<List<GameInfo>>
}