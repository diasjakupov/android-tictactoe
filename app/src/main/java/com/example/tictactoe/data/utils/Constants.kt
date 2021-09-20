package com.example.tictactoe.data.utils

object Constants {
    const val GAME_WEBSOCKET_URL="ws://10.0.2.2:8000/ws/game/"
    const val BASE_API_URL="http://10.0.2.2:8000/api/"
    const val CROSS="X"
    const val CIRCLE="O"
    val BASE_FIELD_VALUES=listOf(
        hashMapOf(1 to ""), hashMapOf(2 to ""), hashMapOf(3 to ""),
        hashMapOf(4 to ""), hashMapOf(5 to ""), hashMapOf(6 to ""),
        hashMapOf(7 to ""), hashMapOf(8 to ""), hashMapOf(9 to "")
    )
}