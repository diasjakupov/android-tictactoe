package com.example.tictactoe.data.utils

sealed class LocalDataState {
    object LOADING:LocalDataState()
    class SUCCESS(val userId:Int):LocalDataState()
    object ERROR : LocalDataState()
}