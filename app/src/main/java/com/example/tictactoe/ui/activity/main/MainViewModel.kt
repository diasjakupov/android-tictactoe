package com.example.tictactoe.ui.activity.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.tictactoe.data.datasources.DataStoreSource
import com.example.tictactoe.data.repository.user.UserRepository
import com.example.tictactoe.data.utils.LocalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    val isDataLoaded = MutableLiveData<LocalDataState>(LocalDataState.LOADING)

    init {
        repository.isDataLoaded.observeForever {
            isDataLoaded.value=it
        }
    }
}