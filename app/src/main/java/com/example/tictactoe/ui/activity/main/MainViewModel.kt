package com.example.tictactoe.ui.activity.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.data.repository.user.UserRepository
import com.example.tictactoe.data.utils.LocalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    val isDataLoaded = repository.isLocalDataLoaded

}