package com.example.tictactoe.data.repository.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.tictactoe.data.datasources.DataStoreSource
import com.example.tictactoe.data.datasources.RemoteDataSource
import com.example.tictactoe.data.models.LoginBody
import com.example.tictactoe.data.models.LoginResponse
import com.example.tictactoe.data.network.NetworkResult
import com.example.tictactoe.data.utils.LocalDataState
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val dataStoreSource: DataStoreSource,
    private val remoteDataSource: RemoteDataSource,
): UserRepository {
    override val userId= MutableLiveData<Int>()
    override val isLocalDataLoaded = MutableLiveData<LocalDataState<Int>>()
    override val isRemoteDataLoaded = MutableLiveData<NetworkResult<LoginResponse>>()

    init {
        dataStoreSource.userData.asLiveData().observeForever {
            userId.value=it
            if(it==-1){
                isLocalDataLoaded.value=LocalDataState.ERROR()
            }else{
                isLocalDataLoaded.value=LocalDataState.SUCCESS(it)
            }
        }
    }

    override suspend fun login(username: String, password: String) {
        isRemoteDataLoaded.postValue(NetworkResult.Loading())
        try {
            val response=remoteDataSource.login(LoginBody(username, password))
            val ntResult=handleNetworkResult(response)
            isRemoteDataLoaded.postValue(ntResult)
        }catch (e:Exception){
            Log.e("TAG", e.toString())
            isRemoteDataLoaded.postValue(NetworkResult.Error(null, "Uncaught error"))
        }
    }

    private suspend fun handleNetworkResult(response: Response<LoginResponse>): NetworkResult<LoginResponse> {
        return when{
            response.code()== 202->{
                dataStoreSource.updateUserId(response.body()!!.userId)
                NetworkResult.Success(response.body())

            }
            response.code()==404->{
                NetworkResult.Error(response.body(), response.body()!!.errors)
            }
            else-> NetworkResult.Error(null, "Uncaught error")
        }
    }
}