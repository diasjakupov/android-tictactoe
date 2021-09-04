package com.example.tictactoe.data.models

data class LoginResponse(
    val userId: Int,
    val login:Boolean,
    val errors: String
)