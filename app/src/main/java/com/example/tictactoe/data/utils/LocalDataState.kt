package com.example.tictactoe.data.utils

sealed class LocalDataState(val userId: Int?=null) {
    object LOADING:LocalDataState()
    class SUCCESS(userId:Int):LocalDataState(userId)
    object ERROR : LocalDataState()
}