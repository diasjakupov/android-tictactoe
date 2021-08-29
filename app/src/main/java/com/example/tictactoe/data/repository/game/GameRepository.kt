package com.example.tictactoe.data.repository.game

interface GameRepository {
    suspend fun createSocket(gameUUID: String, userId: Int)
}