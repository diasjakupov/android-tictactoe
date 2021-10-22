package com.example.tictactoe.ui.fragments.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.data.datasources.DataStoreSource
import com.example.tictactoe.data.repository.game.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates


@HiltViewModel
class PlayMenuViewModel @Inject constructor(
    private val repository: GameRepository,
    private val dataStoreSource: DataStoreSource
): ViewModel(){
    private var userId:Int?=null

    init {
        viewModelScope.launch {
            dataStoreSource.updateUserId()
        }
        dataStoreSource.userData.asLiveData().observeForever {
            userId=it
        }
    }

    fun getRandomCode():String{
        return UUID.randomUUID().toString().slice((0..5)).uppercase()
    }

    fun createGame(gameName:String,gameUUID:String):Boolean{
        if(userId!=null){
            val isSuccessful=repository.createGameInstance(gameName, gameUUID)
            if(isSuccessful){
                repository.createSocket(gameUUID, userId!!)
            }
        }
        return false
    }
}