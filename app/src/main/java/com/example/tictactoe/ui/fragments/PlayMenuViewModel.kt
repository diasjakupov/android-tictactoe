package com.example.tictactoe.ui.fragments

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
import javax.inject.Inject
import kotlin.properties.Delegates


@HiltViewModel
class PlayMenuViewModel @Inject constructor(
    private val repository: GameRepository,
    private val dataStoreSource: DataStoreSource
): ViewModel(){
    private var userId:Int?=1

    init {
        //FIXME("Made a separate logic for this")
        viewModelScope.launch {
            dataStoreSource.updateUserId()
        }
        dataStoreSource.userData.asLiveData().observeForever {
            userId=it
            Log.e("PLAYMENUVIEWMODEL", "inside observer")
        }
    }

    fun connectToGame(code:String="6c1cb5"){
        if(userId!=null){
            viewModelScope.launch(Dispatchers.Default){
                repository.createSocket(code, userId!!)
                Log.e("PLAYMENUVIEWMODEL", "inside connecting")
            }
        }
    }
}