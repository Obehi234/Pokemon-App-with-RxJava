package com.example.pokemonlivedataapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.adapter.MovesAdapter
import com.example.pokemonlivedataapp.model.details.Move

class MovesFragment : Fragment() {
private lateinit var movesRecycler: RecyclerView
private lateinit var movesAdapter : MovesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_moves, container, false)
        movesRecycler = view.findViewById(R.id.rvMoves)
        movesAdapter = MovesAdapter(emptyList())
        movesRecycler.apply {
            layoutManager = GridLayoutManager(context, DEFAULT_BUFFER_SIZE)
            adapter = movesAdapter
        }
        return view
    }

    fun setData(movesList : List<Move>) {
        movesAdapter.moveList = movesList
        movesAdapter.notifyDataSetChanged()


    }

}