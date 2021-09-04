package com.example.tictactoe.data.repository.game

import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.data.models.GameInfo
import com.example.tictactoe.data.network.NetworkResult

interface GameRepository {
    val gameList:MutableLiveData<NetworkResult<List<GameInfo>>>

    fun createSocket(gameUUID: String, userId: Int)
    suspend fun getGameList()
}