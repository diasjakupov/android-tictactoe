package com.example.tictactoe.data.network

import com.example.tictactoe.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserApi {

    @POST("user/login/")
    suspend fun login(@Body login: LoginBody): Response<AuthResponse.LoginResponse>

    @POST("user/register")
    suspend fun register(@Body body: RegisterBody): Response<AuthResponse.RegisterResponse>
}