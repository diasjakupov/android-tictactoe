package com.example.tictactoe.data.models

data class GameInfo(
    val id:Int,
    val code:String,
    val name:String,
    val timestamp:String,
    val movements:String,
    val game_status:String,
    val first_player:Int,
    val second_player: Int,
    val winUser:Int
)