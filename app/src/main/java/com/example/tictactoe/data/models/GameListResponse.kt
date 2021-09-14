package com.example.tictactoe.data.models

data class GameListResponse(
    val result: List<GameInfo>,
    val page_count: Int,
    val next: Int?,
    val current: Int,
    val previous: Int?
)