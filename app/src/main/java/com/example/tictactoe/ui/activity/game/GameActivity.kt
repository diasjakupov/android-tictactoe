package com.example.tictactoe.ui.activity.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.ui.adapters.GameFieldAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_game.*


@AndroidEntryPoint
class GameActivity : AppCompatActivity() {
    private val viewModel: GameViewModel by viewModels()
    private val args: GameActivityArgs by navArgs()
    private lateinit var rv:RecyclerView
    private val adapter: GameFieldAdapter = GameFieldAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        viewModel.connectToGame(args.gameUUID)
        setUI()
    }

    private fun setUI(){
        rv=gameField
        rv.adapter=adapter
        rv.layoutManager=GridLayoutManager(this, 3) //TODO
        adapter.updateData(listOf(
            hashMapOf(1 to "X"), hashMapOf(2 to "O"), hashMapOf(3 to ""),
            hashMapOf(4 to ""), hashMapOf(5 to "X"), hashMapOf(6 to ""),
            hashMapOf(7 to ""), hashMapOf(8 to "O"), hashMapOf(9 to "X")
        ))
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disconnect()
    }
}