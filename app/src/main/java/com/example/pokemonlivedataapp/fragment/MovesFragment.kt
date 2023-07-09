package com.example.pokemonlivedataapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.activities.DataListener
import com.example.pokemonlivedataapp.adapter.AbilityAdapter
import com.example.pokemonlivedataapp.adapter.MovesAdapter
import com.example.pokemonlivedataapp.model.details.Ability
import com.example.pokemonlivedataapp.model.details.Move
import com.example.pokemonlivedataapp.model.details.PokemonDetails
import com.example.pokemonlivedataapp.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovesFragment : Fragment(), DataListener {
    private lateinit var movesRecycler: RecyclerView
    private lateinit var movesAdapter: MovesAdapter
    private lateinit var moveList: List<Move>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_moves, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movesRecycler = view.findViewById(R.id.rvMoves)
        movesAdapter = MovesAdapter(emptyList())
        movesRecycler.adapter = movesAdapter

        try {
            movesAdapter.updateData(moveList)
        } catch (e: Exception) {
            Log.d("CheckData", "${e.message}")
        }

        Log.d("CheckData", "pokemonDetails $moveList")
    }

    override fun sendDataToFragment(data: PokemonDetails) {
        Log.d("CheckData", "sendDataToFragment $data")
        moveList = data.moves
    }

}

