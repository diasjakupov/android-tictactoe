package com.example.tictactoe.ui.fragments.createGame

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.R
import com.example.tictactoe.data.utils.LocalDataState
import com.example.tictactoe.ui.fragments.main.PlayMenuViewModel
import com.neovisionaries.ws.client.WebSocketState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game_create_form.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameCreateFormFragment : DialogFragment() {
    private val viewModel: PlayMenuViewModel by activityViewModels()
    private lateinit var randomGenerator:Button
    private lateinit var gameCodeET: EditText
    private lateinit var gameNameET:EditText
    private lateinit var submitBtn:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView= inflater.inflate(R.layout.fragment_game_create_form, container, false)
        randomGenerator=rootView.gameCodeGenerator
        gameCodeET=rootView.gameCodeET
        gameNameET=rootView.gameNameET
        submitBtn=rootView.createGameBtn
        randomGenerator.setOnClickListener {
            val code=viewModel.getRandomCode()
            gameCodeET.text=Editable.Factory().newEditable(code)
        }
        submitBtn.setOnClickListener {
            createGameInstance()
        }
        observeDataStatus()
        return rootView
    }

    private fun createGameInstance(){
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.createGame(gameNameET.text.toString(), gameCodeET.text.toString())
        }
    }

    private fun observeDataStatus(){
        viewModel.isGameInstanceCreated.observe(viewLifecycleOwner, {
            when(it){
                is LocalDataState.SUCCESS->{
                    val action=GameCreateFormFragmentDirections.actionGameCreateFormFragmentToGameActivity(gameCodeET.text.toString())
                    findNavController().navigate(action)
                }
                else->{
                    Log.e("TAG", "loading")
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        setDimensions()
    }

    private fun setDimensions(){
        val screenWidth=resources.displayMetrics.widthPixels
        val popupHeight=resources.getDimensionPixelSize(R.dimen.popup_height)
        dialog?.window?.setLayout((screenWidth*0.9).toInt(), popupHeight)
    }
}