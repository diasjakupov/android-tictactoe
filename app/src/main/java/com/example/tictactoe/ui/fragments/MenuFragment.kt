package com.example.tictactoe.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.R
import kotlinx.android.synthetic.main.fragment_menu.view.*


class MenuFragment : Fragment() {
    private lateinit var mainPlayBtn: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootView=inflater.inflate(R.layout.fragment_menu, container, false)
        //initialize variables
        mainPlayBtn=rootView.mainPlayBtn

        setUI()
        return rootView
    }

    private fun setUI(){
        setOnClickListeners()
    }

    private fun setOnClickListeners(){
        mainPlayBtn.setOnClickListener {
            val action=MenuFragmentDirections.actionMenuFragmentToPlayMenuFragment()
            findNavController().navigate(action)
        }
    }

}