package com.example.tictactoe.ui.activity.game

import android.util.Log
import androidx.lifecycle.*
import com.example.tictactoe.data.models.GameInfo
import com.example.tictactoe.data.models.GameResult
import com.example.tictactoe.data.models.GameSize
import com.example.tictactoe.data.repository.game.GameRepository
import com.example.tictactoe.data.repository.user.UserRepository
import com.example.tictactoe.data.utils.Constants
import com.example.tictactoe.data.utils.LocalDataState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GameViewModel @Inject constructor(
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository
):ViewModel() {
    private val gameSize=GameSize.NINE
    val isEnded: LiveData<GameResult> = Transformations.map(gameRepository.markedFieldCoords){
        val status=Gson().fromJson<Int>(it.game_status, object:TypeToken<Int>(){}.type)
        return@map GameResult(it.winUser, status)
    }
    val moves: LiveData<MutableList<HashMap<Int, String>>> =
        Transformations.map(gameRepository.markedFieldCoords) {
            val parsedValue=Gson().fromJson<List<HashMap<String, String>>>(it.movements, object: TypeToken<List<HashMap<String, String>>>(){}.type)
            //get only the number of square and the sign
            val processedValue=parsedValue.map { item->
                hashMapOf(item["n"]!!.toInt() to item["sign"]!!)
            } as MutableList<HashMap<Int, String>>?

            val field=when(gameSize){
                GameSize.NINE->Constants.BASE_FIELD_VALUES_9
                GameSize.SIXTEEN->Constants.BASE_FIELD_VALUES_16
                GameSize.TWENTY_FIVE->Constants.BASE_FIELD_VALUES_25
            }
            //find the match in the given base field with the processed value
            val result=field.map { move->
                val first=move.keys.first()
                val found=processedValue!!.filter{match->match.keys.first()==first}
                if(found.isNotEmpty()){
                    return@map found[0]
                }else{
                    return@map move
                }
            }
            return@map result as MutableList<HashMap<Int, String>>?
        }
    var myId=-1

    fun connectToGame(gameUUID:String)=viewModelScope.launch(Dispatchers.IO){
        if(userRepository.isLocalDataLoaded.value is LocalDataState.SUCCESS){
            gameRepository.createSocket(gameUUID, userRepository.userId.value!!)
            myId=userRepository.userId.value!!
        }
    }

    fun disconnect()=viewModelScope.launch(Dispatchers.IO){
        gameRepository.disconnect()
    }

    fun sendMessage(data:String)=viewModelScope.launch(Dispatchers.IO){
        gameRepository.sendData(data)
    }
}