package com.example.tictactoe.ui.fragments.createGame

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.tictactoe.R
import com.example.tictactoe.ui.fragments.main.PlayMenuViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game_create_form.view.*

@AndroidEntryPoint
class GameCreateFormFragment : DialogFragment() {
    private val viewModel: PlayMenuViewModel by activityViewModels()
    private lateinit var randomGenerator:Button
    private lateinit var gameCodeET: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView= inflater.inflate(R.layout.fragment_game_create_form, container, false)
        randomGenerator=rootView.gameCodeGenerator
        gameCodeET=rootView.gameCodeET
        randomGenerator.setOnClickListener {
            val code=viewModel.getRandomCode()
            gameCodeET.text=Editable.Factory().newEditable(code)
        }
        return rootView
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