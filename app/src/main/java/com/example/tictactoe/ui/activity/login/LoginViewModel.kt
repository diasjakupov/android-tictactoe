package com.example.tictactoe.ui.activity.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tictactoe.data.repository.user.UserRepository
import com.example.tictactoe.data.utils.LocalDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    val isDataLoaded = MutableLiveData<LocalDataState>(LocalDataState.LOADING)

    init {
        repository.isDataLoaded.observeForever {
            isDataLoaded.value=it
        }
    }

    fun login(username:String, password:String){
        viewModelScope.launch(Dispatchers.IO){
            repository.login(username, password)

        }
    }
}