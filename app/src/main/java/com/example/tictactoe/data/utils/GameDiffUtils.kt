package com.example.tictactoe.data.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.tictactoe.data.models.GameInfo

class GameDiffUtils (
    private val oldList: List<GameInfo>,
    private val newList: List<GameInfo>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldList[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].code == oldList[oldItemPosition].code
    }
}