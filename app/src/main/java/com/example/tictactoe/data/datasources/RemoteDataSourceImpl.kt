package com.example.tictactoe.data.datasources

import com.example.tictactoe.data.models.*
import com.example.tictactoe.data.network.GameApi
import com.example.tictactoe.data.network.UserApi
import retrofit2.Response
import javax.inject.Inject


class RemoteDataSourceImpl @Inject constructor(
    private val userApi: UserApi,
    private val gameApi: GameApi
):RemoteDataSource{
    override suspend fun login(loginBody: LoginBody): Response<AuthResponse.LoginResponse> {
        return userApi.login(loginBody)
    }

    override suspend fun gameList(): Response<List<GameInfo>> {
        return gameApi.getAllAvailableGames()
    }

    override suspend fun createGameInstance(body: GameCreationParams): Response<String> {
        return gameApi.createGameInstance(body)
    }

    override suspend fun register(registerBody: RegisterBody): Response<AuthResponse.RegisterResponse> {
        return userApi.register(registerBody)
    }

}