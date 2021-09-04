package com.example.tictactoe.data.repository.user

import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.data.models.LoginResponse
import com.example.tictactoe.data.network.NetworkResult
import com.example.tictactoe.data.utils.LocalDataState

interface UserRepository {
    val userId:MutableLiveData<Int>
    val isLocalDataLoaded:MutableLiveData<LocalDataState>
    val isRemoteDataLoaded: MutableLiveData<NetworkResult<LoginResponse>>

    suspend fun login(username:String, password:String)
}