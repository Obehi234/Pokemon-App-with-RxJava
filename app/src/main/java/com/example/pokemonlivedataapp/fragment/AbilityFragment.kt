package com.example.pokemonlivedataapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.adapter.AbilityAdapter
import com.example.pokemonlivedataapp.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AbilityFragment : Fragment() {

    private lateinit var abilityRecycler: RecyclerView
    private lateinit var abilityAdapter: AbilityAdapter
    private val compositeDisposable = CompositeDisposable()
    private var pokemonName = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ability, container, false)

        abilityRecycler = view.findViewById(R.id.rvAbility)
        abilityAdapter = AbilityAdapter(emptyList())
        abilityRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), DEFAULT_BUFFER_SIZE)
            adapter = abilityAdapter
        }

        pokemonName = arguments?.getString(ARG_POKEMON_NAME) ?: ""
        Log.d("POKEMONNAME", "Ability: $pokemonName")
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
                        Log.d("Network Call Ability Scuucess", "Success: ${pokemonDetails.abilities}")
                        abilityAdapter.updateData(pokemonDetails.abilities)
                    },
                    {error ->
                        Log.d("Network Call Ability Failed!","Error: $error")

                    }
                )
        )
    }
companion object{
    private const val ARG_POKEMON_NAME = "pokemonName"

    fun newInstance(pokemonName: String) : AbilityFragment {
        val fragment = AbilityFragment()
        val args = Bundle().apply {
            putString(ARG_POKEMON_NAME, pokemonName)
            Log.d("POKEMONNAME 1", "Ability: $pokemonName")
        }
        fragment.arguments = args
        return fragment
    }
}


}
