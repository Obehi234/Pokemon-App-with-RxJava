package com.example.pokemonlivedataapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.model.details.Ability

class AbilityAdapter(var abilityList: List<Ability>) :
    RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val abilityName: TextView = itemView.findViewById(R.id.formName)

        fun bind(ability: Ability) {
            abilityName.text = ability.ability.name
            Log.d("CheckData", " bind $abilityList")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.forms_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(abilityList[position])
        Log.d("CheckData", " onBindViewHolder $abilityList")
    }

    override fun getItemCount(): Int {
        Log.d("CheckData", " size ${abilityList.size}")
        return abilityList.size
    }

    fun updateData(newList: List<Ability>) {
        abilityList = newList
        Log.d("CheckData", " abilityList $abilityList")
        Log.d("CheckData", " newList $newList")

        notifyDataSetChanged()

        Log.d("CheckData", "notifyDataSetChanged called")
    }

}