package com.example.tictactoe.ui.fragments.gameList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.data.network.NetworkResult
import com.example.tictactoe.ui.adapters.GameListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game_list.view.*

@AndroidEntryPoint
class GameListFragment : Fragment() {
    private val viewModel:GameListViewModel by activityViewModels()
    private lateinit var adapter:GameListAdapter
    private lateinit var gameRV:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.fragment_game_list, container, false)
        //get data
        viewModel.getGameList()
        gameRV=rootView.gameRV
        adapter= GameListAdapter()
        gameRV.adapter=adapter
        gameRV.layoutManager=LinearLayoutManager(requireContext())

        //set UIData
        setUIData()
        return rootView
    }

    private fun setUIData(){
        //set observer to gameList
        viewModel.gameList.observe(viewLifecycleOwner, {
            when(it){
                is NetworkResult.Loading->{}
                is NetworkResult.Error->{}
                is NetworkResult.Success->{
                    adapter.updateData(it.data!!)
                }
            }
        })
    }

}