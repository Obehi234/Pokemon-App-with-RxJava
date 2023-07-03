package com.example.pokemonlivedataapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.model.details.Form
import com.example.pokemonlivedataapp.model.details.PokemonDetails

class FormsRecyclerAdapter(private val formsList: List<Form>):
RecyclerView.Adapter<FormsRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val formsImage: ImageView = itemView.findViewById(R.id.pokemonFormImage)
        val formName: TextView = itemView.findViewById(R.id.formName)

        fun bind(form : Form) {
            val context = itemView.context
            formName.text = form.name

            Glide.with(context)
                .load(form.url)
                .into(formsImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.forms_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val formDisplay = formsList[position]
        holder.bind(formDisplay)

    }

    override fun getItemCount(): Int  = formsList.size
}