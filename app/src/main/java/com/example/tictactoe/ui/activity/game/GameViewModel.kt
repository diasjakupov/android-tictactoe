package com.example.tictactoe.ui.activity.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.data.repository.game.GameRepository
import com.example.tictactoe.data.repository.user.UserRepository
import com.example.tictactoe.data.utils.LocalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository
):ViewModel() {

    fun connectToGame(gameUUID:String)=viewModelScope.launch(Dispatchers.IO){
        if(userRepository.isLocalDataLoaded.value is LocalDataState.SUCCESS){
            gameRepository.createSocket(gameUUID, userRepository.userId.value!!)
        }
    }

    fun disconnect()=viewModelScope.launch(Dispatchers.IO){

    }
}