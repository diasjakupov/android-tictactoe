package com.example.tictactoe.ui.fragments.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    val isDataLoaded = repository.isLocalDataLoaded
    val isRemoteDataLoaded = repository.isRemoteDataLoaded

    fun login(username:String, password:String){
        viewModelScope.launch(Dispatchers.IO){
            repository.login(username, password)
        }
    }


    fun register(username: String, password: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.register(username, password)
        }
    }
}