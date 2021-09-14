package com.example.tictactoe.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.data.models.GameInfo
import com.example.tictactoe.data.utils.GameDiffUtils
import com.example.tictactoe.ui.fragments.gameList.GameListFragmentDirections
import kotlinx.android.synthetic.main.game_row_item.view.*

class GameListAdapter(
    private val fragment:Fragment
): RecyclerView.Adapter<GameListAdapter.ViewHolder>() {
    private var gameList= emptyList<GameInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.game_row_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int = gameList.size

    fun updateData(newList:List<GameInfo>){
        val gameDiffUtils=GameDiffUtils(newList, gameList)
        val diffUtilsResult=DiffUtil.calculateDiff(gameDiffUtils)
        gameList=newList
        diffUtilsResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(data:GameInfo){
            itemView.gameNameTV.text=data.name
            itemView.gameCreatorTV.text=data.first_player.toString()

            itemView.gameConnectBtn.setOnClickListener {
                val action=GameListFragmentDirections.actionGameListFragmentToGameActivity(data.code)
                fragment.findNavController().navigate(action)
            }
        }
    }
}