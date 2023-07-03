package com.example.pokemonlivedataapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.model.details.Species

class SpeciesAdapter(val speciesList : List<Species>):
    RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {

        inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
            val speciesName: TextView = itemView.findViewById(R.id.formName)

            fun bind(species: Species) {
                speciesName.text = species.name
            }
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.forms_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speciesDisplay = speciesList[position]
        holder.bind(speciesDisplay)
    }

    override fun getItemCount(): Int = speciesList.size
}