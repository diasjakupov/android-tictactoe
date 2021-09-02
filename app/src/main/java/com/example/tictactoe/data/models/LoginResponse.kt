package com.example.tictactoe.data.models

data class LoginResponse(
    val userId: Int,
    val isLogged:Boolean,
    val error: String
)