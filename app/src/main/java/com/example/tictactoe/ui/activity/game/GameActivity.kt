package com.example.tictactoe.ui.activity.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.data.utils.Constants
import com.example.tictactoe.ui.adapters.GameFieldAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


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
        lifecycleScope.launch {
            delay(5000)
            viewModel.sendMessage("HEllo")
        }
    }

    private fun setUI(){
        rv=gameField
        rv.adapter=adapter
        rv.layoutManager=GridLayoutManager(this, 3) //TODO
        adapter.updateData(Constants.BASE_FIELD_VALUES)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disconnect()
    }
}