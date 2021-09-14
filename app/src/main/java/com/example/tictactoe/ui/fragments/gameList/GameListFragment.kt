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
import com.example.tictactoe.databinding.FragmentGameListBinding
import com.example.tictactoe.ui.adapters.GameListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_game_list.view.*

@AndroidEntryPoint
class GameListFragment : Fragment() {
    private val viewModel:GameListViewModel by activityViewModels()
    private lateinit var adapter:GameListAdapter
    private lateinit var gameRV:RecyclerView
    private var _binding: FragmentGameListBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentGameListBinding.inflate(inflater)
        //get data
        viewModel.getGameList()
        gameRV=binding.gameRV
        adapter= GameListAdapter(this)
        gameRV.adapter=adapter
        gameRV.layoutManager=LinearLayoutManager(requireContext())

        //set UIData
        setUIData()
        return binding.root
    }

    private fun setUIData(){
        //set observer to gameList
        viewModel.gameList.observe(viewLifecycleOwner, {
            when(it){
                is NetworkResult.Loading->{
                    binding.gameRV.visibility=View.GONE
                    binding.gameProgressBar.visibility=View.VISIBLE
                    binding.gameError.visibility=View.GONE
                }
                is NetworkResult.Error->{
                    binding.gameRV.visibility=View.GONE
                    binding.gameProgressBar.visibility=View.GONE
                    binding.gameError.visibility=View.VISIBLE
                }
                is NetworkResult.Success->{
                    binding.gameRV.visibility=View.VISIBLE
                    binding.gameProgressBar.visibility=View.GONE
                    binding.gameError.visibility=View.GONE
                    adapter.updateData(it.data!!)
                }
            }
        })
    }

}