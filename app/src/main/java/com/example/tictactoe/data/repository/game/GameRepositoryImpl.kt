package com.example.tictactoe.data.repository.game

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tictactoe.data.datasources.RemoteDataSource
import com.example.tictactoe.data.models.GameCreationParams
import com.example.tictactoe.data.models.GameInfo
import com.example.tictactoe.data.network.NetworkResult
import com.example.tictactoe.data.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.neovisionaries.ws.client.*
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


class GameRepositoryImpl @Inject constructor(
    private val factory: WebSocketFactory,
    private val remoteDataSource: RemoteDataSource,
):GameRepository {
    private var webSocketClient: WebSocket? = null
    override val webSocketState= MutableLiveData<WebSocketState>()
    override val markedFieldCoords= MutableLiveData<GameInfo>()
    override val userSign= MutableLiveData<String>()
    override val gameList=MutableLiveData<NetworkResult<List<GameInfo>>>()

    override fun createSocket(gameUUID: String, userId: Int){
        webSocketClient=factory.createSocket("${Constants.GAME_WEBSOCKET_URL}$gameUUID/?id=$userId")
        webSocketClient!!.connect()
        addWebSocketListener()
    }

    override suspend fun createGameInstance(gameName: String, gameUUID: String, token: String): Boolean{
        val response=remoteDataSource.createGameInstance(GameCreationParams(gameName, gameUUID),"Token $token")
        Log.e("TAG", "${response.body()}")
        return when(handleNetworkResult(response)){
            is NetworkResult.Success->true
            is NetworkResult.Error->false
            else->false
        }
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
        webSocketClient!!.sendText(Gson().toJson(hashMapOf("message" to data)))
    }

    private fun <T> handleNetworkResult(response: Response<T>): NetworkResult<T>{
        return if(response.isSuccessful){
            NetworkResult.Success(response.body())
        }else{
            NetworkResult.Error(null, response.message())
        }
    }

    private fun addWebSocketListener(){
        webSocketClient!!.addListener(object: WebSocketAdapter(){
            override fun onError(websocket: WebSocket?, cause: WebSocketException?) {
                webSocketState.postValue(WebSocketState.CLOSED)
            }


            override fun onSendingHandshake(
                websocket: WebSocket?,
                requestLine: String?,
                headers: MutableList<Array<String>>?
            ) {
                webSocketState.postValue(WebSocketState.CONNECTING)
                super.onSendingHandshake(websocket, requestLine, headers)
            }

            override fun onTextMessage(websocket: WebSocket?, data: ByteArray?) {
                super.onTextMessage(websocket, data)
            }

            override fun onConnected(
                websocket: WebSocket?,
                headers: MutableMap<String, MutableList<String>>?
            ) {
                super.onConnected(websocket, headers)
                webSocketState.postValue(WebSocketState.OPEN)
            }
            override fun onTextMessage(websocket: WebSocket?, text: String?) {
                if (text != null) {
                    val message=Gson().fromJson<HashMap<String, String>>(text, object: TypeToken<HashMap<String, String>>(){}.type)
                    if(message.containsKey("message")){
                        val parsedValue=Gson().fromJson<GameInfo>(message["message"], object: TypeToken<GameInfo>(){}.type)
                        markedFieldCoords.postValue(parsedValue)
                    }else if(message.containsKey("sign")){
                        userSign.postValue(message["sign"])
                    }

                }
            }
        })
    }

}