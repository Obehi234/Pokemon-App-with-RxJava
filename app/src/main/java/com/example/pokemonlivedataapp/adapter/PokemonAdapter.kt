package com.example.pokemonlivedataapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.model.PokemonResponse
import com.example.pokemonlivedataapp.model.Result

class PokemonAdapter(
    private var pokemonList: List<Result>,
    private val onItemClick: (Result)-> Unit):RecyclerView.Adapter<PokemonAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonImage: ImageView = itemView.findViewById(R.id.move_image)
        val pokemonName: TextView = itemView.findViewById(R.id.move_name)
        val pokemonNumber: TextView = itemView.findViewById(R.id.pokemonNumber)

        fun bind(pokemon: Result) {
            val context = itemView.context
            val url = pokemon.url
            val imageNo = url.split("https://pokeapi.co/api/v2/pokemon/")[1].split("/")[0]
            Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$imageNo.png")
                .into(pokemonImage)

            pokemonName.text = pokemon.name
        }

        fun setSerialNumber(number: Int) {
            val formattedNumber = String.format("#%03d", number)
            pokemonNumber.text = formattedNumber
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    val clickedPokemon = pokemonList[position]
                    onItemClick(clickedPokemon)
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        val serialNumber = position + 1

        holder.setSerialNumber(serialNumber)
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int= pokemonList.size

    fun updateData(newList: List<com.example.pokemonlivedataapp.model.Result>) {
        pokemonList = newList
        notifyDataSetChanged()

    }
}