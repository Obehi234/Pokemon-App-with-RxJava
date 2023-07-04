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
import com.example.pokemonlivedataapp.adapter.StatsAdapter
import com.example.pokemonlivedataapp.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StatsFragment : Fragment() {
    private lateinit var statsRecycler: RecyclerView
    private lateinit var statsAdapter: StatsAdapter
    private val compositeDisposable = CompositeDisposable()
    private var pokemonName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pokemonName = arguments?.getString(ARG_POKEMON_NAME) ?: ""
        Log.d("NewPOKEMONNAME", "Stats: $pokemonName")

        if (pokemonName.isNotEmpty()) {
            fetchPokemonDetails(pokemonName)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        statsRecycler = view.findViewById(R.id.rvStats)
        statsAdapter = StatsAdapter(emptyList())

        statsRecycler.apply {

            layoutManager = GridLayoutManager(context, DEFAULT_BUFFER_SIZE)
            adapter = statsAdapter
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
                        statsAdapter.updateData(pokemonDetails.stats)
                        Log.d("Network Call Ability Scuucess", "Success: ${pokemonDetails.stats}")

                    },
                    {error ->
                        Log.d("Ability Network Call Failed!","Error: $error")

                    }
                )
        )

    }

    companion object{
        private const val ARG_POKEMON_NAME = "pokemonName"
        fun newInstance(pokemonName: String) : StatsFragment {
            val fragment = StatsFragment()
            val args = Bundle().apply {
                putString(ARG_POKEMON_NAME, pokemonName)
                Log.d("POKEMONNAME 1", "Stats: $pokemonName")
            }
            fragment.arguments = args
            return fragment
        }
    }
}
