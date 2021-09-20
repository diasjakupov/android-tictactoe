package com.example.tictactoe.data.repository.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.data.datasources.DataStoreSource
import com.example.tictactoe.data.datasources.RemoteDataSource
import com.example.tictactoe.data.models.GameInfo
import com.example.tictactoe.data.network.NetworkResult
import com.example.tictactoe.data.utils.Constants
import com.google.gson.Gson
import com.neovisionaries.ws.client.*
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


class GameRepositoryImpl @Inject constructor(
    private val factory: WebSocketFactory,
    private val remoteDataSource: RemoteDataSource
    ):GameRepository {
    private var webSocketClient: WebSocket? = null
    override val gameList=MutableLiveData<NetworkResult<List<GameInfo>>>()

    override fun createSocket(gameUUID: String, userId: Int){
        webSocketClient=factory.createSocket("${Constants.GAME_WEBSOCKET_URL}$gameUUID/?id=$userId")
        webSocketClient!!.connect()
        addWebSocketListener()
    }

    override fun disconnect() {
        webSocketClient!!.disconnect()
    }

    override suspend fun getGameList() {
        gameList.postValue(NetworkResult.Loading())
        try {
            val response=remoteDataSource.gameList()
            val ntResult=handleNetworkResult(response)
            gameList.postValue(ntResult)
        }catch (e:Exception){
            gameList.postValue(NetworkResult.Error(null, "Uncaught error"))
        }
    }

    override suspend fun sendData(data: String) {
        webSocketClient!!.sendText(Gson().toJson(hashMapOf("message" to "1,3")))
        Log.e("TAG", "${webSocketClient!!.uri}")
    }

    private fun handleNetworkResult(response: Response<List<GameInfo>>): NetworkResult<List<GameInfo>>{
        return if(response.isSuccessful){
            NetworkResult.Success(response.body())
        }else{
            Log.e("GameRepository", response.message())
            NetworkResult.Error(null, "Error")
        }
    }

    private fun addWebSocketListener(){
        webSocketClient!!.addListener(object: WebSocketAdapter(){
            override fun onError(websocket: WebSocket?, cause: WebSocketException?) {
                Log.e("TAG", cause?.message!!)
            }

            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                if (text != null) {
                    Log.e("TAG", text)
                }
            }
        })
    }

}