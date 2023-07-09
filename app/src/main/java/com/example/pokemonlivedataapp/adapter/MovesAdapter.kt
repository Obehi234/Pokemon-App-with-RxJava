package com.example.pokemonlivedataapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.model.details.Move


class MovesAdapter(var moveList: List<Move>) : RecyclerView.Adapter<MovesAdapter.ViewHolder>()  {
    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){
        val moveName: TextView = itemView.findViewById(R.id.formName)

        fun bind(move: Move) {
            moveName.text = move.move.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.forms_recycler_item, parent, false)
        val viewHolder = ViewHolder(itemView)
        viewHolder.itemView.background.setTint(getRandomColor())
        return viewHolder
    }

    private fun getRandomColor(): Int {
        val cardColors = arrayOf("#072AC8", "#EEC8E0", "#60D394")
        val randomIndex = (cardColors.indices).random()
        return Color.parseColor(cardColors[randomIndex])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val move = moveList[position]
        holder.bind(move)
    }

    override fun getItemCount(): Int = moveList.size

    fun updateData(newList: List<Move>) {
        moveList = newList
        notifyDataSetChanged()
    }
}