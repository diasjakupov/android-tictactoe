package com.example.tictactoe.data.repository.user

class UserRepositoryImpl: UserRepository {


    override suspend fun login(username: String, password: String): Int {
        return 0
    }
}