package com.example.tictactoe.ui.fragments.gameList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.data.repository.game.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameListViewModel @Inject constructor(
    private val repository: GameRepository
):ViewModel(){
    val gameList=repository.gameList

    fun getGameList()=viewModelScope.launch(Dispatchers.IO){
        repository.getGameList()
    }
}