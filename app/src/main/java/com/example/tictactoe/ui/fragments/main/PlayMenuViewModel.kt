package com.example.tictactoe.ui.fragments.main

import androidx.lifecycle.*
import com.example.tictactoe.data.datasources.DataStoreSource
import com.example.tictactoe.data.repository.game.GameRepository
import com.example.tictactoe.data.utils.LocalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


@HiltViewModel
class PlayMenuViewModel @Inject constructor(
    private val repository: GameRepository,
    private val dataStoreSource: DataStoreSource
) : ViewModel() {
    private var userId: Int? = null
    private var token: String? = null
    val isGameInstanceCreated = MutableLiveData<LocalDataState<Boolean>>()

    init {
        viewModelScope.launch {
            dataStoreSource.updateUserId()
        }
        dataStoreSource.userId.asLiveData().observeForever {
            userId = it
        }
        dataStoreSource.userToken.asLiveData().observeForever {
            token=it
        }
    }

    fun getRandomCode(): String {
        return UUID.randomUUID().toString().slice((0..5)).uppercase()
    }


    suspend fun createGame(gameName: String, gameUUID: String): Boolean {
        isGameInstanceCreated.postValue(LocalDataState.LOADING())
        if (userId != null && token != null) {
            val isSuccessful = repository.createGameInstance(gameName, gameUUID, token!!)
            if (isSuccessful) {
                isGameInstanceCreated.postValue(LocalDataState.SUCCESS(isSuccessful))
                return true
            }else{
                isGameInstanceCreated.postValue(LocalDataState.ERROR())
            }
        }
        return false
    }
}