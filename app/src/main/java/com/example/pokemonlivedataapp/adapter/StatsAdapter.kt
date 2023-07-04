package com.example.pokemonlivedataapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.model.details.Stat

class StatsAdapter(var statsList: List<Stat>):
RecyclerView.Adapter<StatsAdapter.StatsViewHolder>(){

    inner class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statsName : TextView = itemView.findViewById(R.id.formName)

        fun bind(stat: Stat) {
            statsName.text = stat.stat.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.forms_recycler_item, parent, false)
        return StatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(statsList[position])
    }

    override fun getItemCount(): Int = statsList.size

    fun updateData(newList: List<Stat>) {
        statsList = newList
        notifyDataSetChanged()

    }
}