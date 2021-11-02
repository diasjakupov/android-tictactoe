package com.example.tictactoe.data.repository.game

import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.data.models.GameInfo
import com.example.tictactoe.data.network.NetworkResult
import com.neovisionaries.ws.client.WebSocketState

interface GameRepository {
    val gameList:MutableLiveData<NetworkResult<List<GameInfo>>>
    val markedFieldCoords: MutableLiveData<GameInfo>
    val userSign: MutableLiveData<String>
    val webSocketState: MutableLiveData<WebSocketState>
    fun createSocket(gameUUID: String, userId: Int)
    fun disconnect()
    suspend fun getGameList()
    suspend fun createGameInstance(gameName: String, gameUUID: String, token: String): Boolean
    suspend fun sendData(data:String)
}