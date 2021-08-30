package com.example.tictactoe.data.repository.user

interface UserRepository {

    suspend fun login(username:String, password:String): Int
}