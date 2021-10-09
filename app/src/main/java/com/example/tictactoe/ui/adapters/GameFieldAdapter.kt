package com.example.tictactoe.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tictactoe.R
import com.example.tictactoe.data.utils.Constants
import com.example.tictactoe.ui.activity.game.GameViewModel
import kotlinx.android.synthetic.main.game_field_item.view.*

class GameFieldAdapter(private val viewModel:GameViewModel): RecyclerView.Adapter<GameFieldAdapter.ViewHolder>()
{
    private var listOfUsedSquares= emptyList<HashMap<Int, String>>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameFieldAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.game_field_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameFieldAdapter.ViewHolder, position: Int) {
        holder.bind(listOfUsedSquares[position], position+1)
    }

    override fun getItemCount(): Int {
        return listOfUsedSquares.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData:List<HashMap<Int, String>>){
        listOfUsedSquares=newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(data: HashMap<Int, String>, position: Int){
            val key=data.keys.first()
            if(key==position){
                if(data[key]==Constants.CROSS){
                    itemView.fieldIV.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_cross))
                }else if(data[key]==Constants.CIRCLE){
                    itemView.fieldIV.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_circle))
                }
            }

            itemView.setOnClickListener {
                viewModel.sendMessage("$position")
            }
        }
    }
}