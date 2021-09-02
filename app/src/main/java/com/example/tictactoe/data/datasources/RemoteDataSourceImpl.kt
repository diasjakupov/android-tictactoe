package com.example.tictactoe.data.datasources

import com.example.tictactoe.data.models.LoginBody
import com.example.tictactoe.data.models.LoginResponse
import com.example.tictactoe.data.network.UserApi
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi
):RemoteDataSource{
    override suspend fun login(loginBody: LoginBody): Response<LoginResponse> {
        return userApi.login(loginBody)
    }

}