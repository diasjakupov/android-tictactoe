package com.example.tictactoe.data.network

import com.example.tictactoe.data.models.LoginBody
import com.example.tictactoe.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserApi {

    @POST("/user/login")
    suspend fun login(@Body login: LoginBody): Response<LoginResponse>
}