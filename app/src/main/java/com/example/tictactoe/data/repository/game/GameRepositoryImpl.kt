package com.example.tictactoe.data.repository.game

import com.example.tictactoe.data.datasources.DataStoreSource
import com.example.tictactoe.data.utils.Constants
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketFactory
import javax.inject.Inject


class GameRepositoryImpl @Inject constructor(
    private val factory: WebSocketFactory
    ):GameRepository {
    private var webSocketClient: WebSocket? = null

    override fun createSocket(gameUUID: String, userId: Int){
        webSocketClient=factory.createSocket("${Constants.GAME_WEBSOCKET_URL}$gameUUID/?id=$userId")
        webSocketClient!!.connectAsynchronously()
    }

}