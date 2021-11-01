package com.example.tictactoe.data.repository.user

import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.data.models.AuthResponse
import com.example.tictactoe.data.network.NetworkResult
import com.example.tictactoe.data.utils.LocalDataState

interface UserRepository {
    val userId:MutableLiveData<Int>
    val userToken:MutableLiveData<String>
    val isLocalDataLoaded:MutableLiveData<LocalDataState<Int>>
    val isRemoteDataLoaded: MutableLiveData<NetworkResult<AuthResponse>>

    suspend fun login(username:String, password:String)
    suspend fun register(username: String, password: String)
}