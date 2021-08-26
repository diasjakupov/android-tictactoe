package com.example.tictactoe.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.R

class PlayMenuFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_menu, container, false)
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