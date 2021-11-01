package com.example.tictactoe.data.models

sealed class AuthResponse(userId:Int, errors:String){
    class RegisterResponse(val userId: Int, val token:String, val errors: String): AuthResponse(userId, errors)
    class LoginResponse(val userId: Int, val login:Boolean, val errors: String): AuthResponse(userId, errors)
}
