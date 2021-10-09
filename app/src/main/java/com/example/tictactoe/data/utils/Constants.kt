package com.example.tictactoe.data.utils

object Constants {
    const val GAME_WEBSOCKET_URL = "ws://10.0.2.2:8000/ws/game/"
    const val BASE_API_URL = "http://10.0.2.2:8000/api/"
    const val CROSS = "X"
    const val CIRCLE = "O"
    val BASE_FIELD_VALUES_9 = listOf(
        hashMapOf(1 to ""), hashMapOf(2 to ""), hashMapOf(3 to ""),
        hashMapOf(4 to ""), hashMapOf(5 to ""), hashMapOf(6 to ""),
        hashMapOf(7 to ""), hashMapOf(8 to ""), hashMapOf(9 to "")
    )
    val BASE_FIELD_VALUES_16 = listOf(
        hashMapOf(1 to ""), hashMapOf(2 to ""), hashMapOf(3 to ""),
        hashMapOf(4 to ""), hashMapOf(5 to ""), hashMapOf(6 to ""),
        hashMapOf(7 to ""), hashMapOf(8 to ""), hashMapOf(9 to ""),
        hashMapOf(10 to ""), hashMapOf(11 to ""), hashMapOf(12 to ""),
        hashMapOf(13 to ""), hashMapOf(14 to ""), hashMapOf(15 to ""),
        hashMapOf(16 to "")
    )
    val BASE_FIELD_VALUES_25 = listOf(
        hashMapOf(1 to ""), hashMapOf(2 to ""), hashMapOf(3 to ""),
        hashMapOf(4 to ""), hashMapOf(5 to ""), hashMapOf(6 to ""),
        hashMapOf(7 to ""), hashMapOf(8 to ""), hashMapOf(9 to ""),
        hashMapOf(10 to ""), hashMapOf(11 to ""), hashMapOf(12 to ""),
        hashMapOf(13 to ""), hashMapOf(14 to ""), hashMapOf(15 to ""),
        hashMapOf(16 to ""),
        hashMapOf(17 to ""), hashMapOf(18 to ""), hashMapOf(19 to ""),
        hashMapOf(20 to ""), hashMapOf(21 to ""), hashMapOf(22 to ""),
        hashMapOf(23 to ""), hashMapOf(24 to ""), hashMapOf(25 to "")
    )
}