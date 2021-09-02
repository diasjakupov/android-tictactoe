package com.example.tictactoe.data.repository.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.tictactoe.data.datasources.DataStoreSource
import com.example.tictactoe.data.datasources.RemoteDataSource
import com.example.tictactoe.data.models.LoginBody
import com.example.tictactoe.data.utils.LocalDataState
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val dataStoreSource: DataStoreSource,
    private val remoteDataSource: RemoteDataSource
): UserRepository {
    override val userId= MutableLiveData<Int>()
    override val isDataLoaded = MutableLiveData<LocalDataState>(LocalDataState.LOADING)

    init {
        dataStoreSource.userData.asLiveData().observeForever {
            Log.e("UserRepository", "userID $it")
            userId.value=it
            if(it==-1){
                isDataLoaded.value=LocalDataState.ERROR
            }else{
                isDataLoaded.value=LocalDataState.SUCCESS(it)
            }
        }
    }

    override suspend fun login(username: String, password: String) {
        val response=remoteDataSource.login(LoginBody(username, password))
        //TODO(Implement three states of network result)
    }
}