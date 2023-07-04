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
import com.example.pokemonlivedataapp.adapter.MovesAdapter
import com.example.pokemonlivedataapp.model.details.Move
import com.example.pokemonlivedataapp.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovesFragment : Fragment() {
private lateinit var movesRecycler: RecyclerView
private lateinit var movesAdapter : MovesAdapter
private val compositeDisposable = CompositeDisposable()
    private var pokemonName = ""


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
        pokemonName = arguments?.getString(ARG_POKEMON_NAME) ?: ""
        if (pokemonName.isNotEmpty()) {
            fetchPokemonDetails(pokemonName)
        }
        return view
    }

    private fun fetchPokemonDetails(pokemonName: String) {
        compositeDisposable.add(
            PokemonRepository.getPokemonDetails(pokemonName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {pokemonDetails ->
                        movesAdapter.updateData(pokemonDetails.moves)
                        Log.d("Network Call Ability Scuucess", "Success: ${pokemonDetails.moves}")

                    },
                    {error ->
                        Log.d("Ability Network Call Failed!","Error: $error")

                    }
                )
        )
    }

    companion object{
        private const val ARG_POKEMON_NAME = "pokemonName"
        fun newInstance(pokemonName: String) : MovesFragment {
            val fragment = MovesFragment()
            val args = Bundle().apply {
                putString(ARG_POKEMON_NAME, pokemonName)
                Log.d("POKEMONNAME 1", "Moves: $pokemonName")
            }
            fragment.arguments = args
            return fragment
        }
    }

}

