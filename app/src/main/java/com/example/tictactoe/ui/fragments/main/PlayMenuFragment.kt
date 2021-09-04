package com.example.tictactoe.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_play_menu.view.*

@AndroidEntryPoint
class PlayMenuFragment : DialogFragment() {
    private lateinit var findGameBtn:Button
    private val viewModel: PlayMenuViewModel by activityViewModels()

    //FIXME("create a separate fragment dedicated to list of lobbies")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView= inflater.inflate(R.layout.fragment_play_menu, container, false)
        findGameBtn=rootView.findGameBtn

        findGameBtn.setOnClickListener {
            showListOfGames()
        }
        // Inflate the layout for this fragment
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

    private fun showListOfGames(){
        val action=PlayMenuFragmentDirections.actionPlayMenuFragmentToGameListFragment()
        findNavController().navigate(action)
    }

}