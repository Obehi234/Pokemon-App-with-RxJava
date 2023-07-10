package com.example.pokemonlivedataapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.model.details.Type

class FormsRecyclerAdapter(private var formsList: List<Type>) :

    RecyclerView.Adapter<FormsRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val formName: TextView = itemView.findViewById(R.id.formName)

        fun bind(type: Type) {
            formName.text = type.type.name

            Log.d("CheckData", " bind $formsList")
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
        Log.d("CheckData", " onBindViewHolder $formsList")

    }

    override fun getItemCount(): Int = formsList.size

    fun updateData(newList: List<Type>) {
        formsList = newList
        notifyDataSetChanged()
        Log.d("CheckData", " formList $formsList")
        Log.d("CheckData", " newList $newList")

        notifyDataSetChanged()

        Log.d("CheckData", "notifyDataSetChanged called")

    }
}