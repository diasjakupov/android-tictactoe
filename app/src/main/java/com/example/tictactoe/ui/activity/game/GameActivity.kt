package com.example.tictactoe.ui.activity.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.data.utils.Constants
import com.example.tictactoe.ui.adapters.GameFieldAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class GameActivity : AppCompatActivity() {
    private val viewModel: GameViewModel by viewModels()
    private val args: GameActivityArgs by navArgs()
    private lateinit var rv:RecyclerView
    private lateinit var adapter: GameFieldAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        adapter= GameFieldAdapter(viewModel)
        viewModel.connectToGame(args.gameUUID)
        setUI()
    }

    private fun setUI(){
        rv=gameField
        rv.adapter=adapter
        rv.layoutManager=GridLayoutManager(this, 3) //TODO
        adapter.updateData(Constants.BASE_FIELD_VALUES_9)
        observingNewMoves()
        observingGameStatus()
    }

    private fun observingNewMoves(){
        viewModel.moves.observe(this, {
            adapter.updateData(it)
        })
    }

    private fun observingGameStatus(){
        viewModel.isEnded.observe(this, {
            if(it.gameStatus>0){
                rv.isEnabled=false
                viewModel.moves.removeObservers(this)
                if(it.winnerUser==viewModel.myId){
                    Log.e("TAG", "YOU WIN")
                }else{
                    Log.e("TAG", "YOU LOSE")
                }
            }else if(it.gameStatus==-1){
                rv.isEnabled=false
                viewModel.moves.removeObservers(this)
                Log.e("TAG", "DRAW")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disconnect()
    }
}