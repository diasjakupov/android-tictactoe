package com.example.tictactoe.data.repository.game

interface GameRepository {
    fun createSocket(gameUUID: String, userId: Int)
}