package com.example.tictactoe.data.repository.user

import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.data.utils.LocalDataState

interface UserRepository {
    val userId:MutableLiveData<Int>
    val isDataLoaded:MutableLiveData<LocalDataState>

    suspend fun login(username:String, password:String)
}