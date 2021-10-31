package com.example.tictactoe.data.utils

sealed class LocalDataState<T>(val userId: T?=null) {
    class LOADING<T>:LocalDataState<T>()
    class SUCCESS<T>(userId:T):LocalDataState<T>(userId)
    class ERROR<T> : LocalDataState<T>()
}