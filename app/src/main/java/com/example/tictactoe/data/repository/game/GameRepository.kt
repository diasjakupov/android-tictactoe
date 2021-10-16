package com.example.tictactoe.data.repository.game

import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.data.models.GameInfo
import com.example.tictactoe.data.network.NetworkResult

interface GameRepository {
    val gameList:MutableLiveData<NetworkResult<List<GameInfo>>>
    val markedFieldCoords: MutableLiveData<GameInfo>
    val userSign: MutableLiveData<String>
    fun createSocket(gameUUID: String, userId: Int)
    fun disconnect()
    suspend fun getGameList()
    suspend fun sendData(data:String)
}