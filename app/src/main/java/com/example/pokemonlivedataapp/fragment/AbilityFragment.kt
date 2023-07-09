package com.example.pokemonlivedataapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.activities.DataListener
import com.example.pokemonlivedataapp.adapter.AbilityAdapter
import com.example.pokemonlivedataapp.model.details.Ability
import com.example.pokemonlivedataapp.model.details.PokemonDetails
import io.reactivex.disposables.CompositeDisposable

class AbilityFragment: Fragment(), DataListener {
    private lateinit var abilityAdapter: AbilityAdapter
    private lateinit var pokemonAbilities:List<Ability>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val abilityRecycler: RecyclerView = view.findViewById(R.id.rvAbility)
        abilityAdapter = AbilityAdapter(emptyList())
        abilityRecycler.adapter = abilityAdapter

        try {
            abilityAdapter.updateData(pokemonAbilities)
        } catch (e: Exception) {
            Log.d("CheckData", "${e.message}")
        }


        Log.d("CheckData", "pokemonDetails $pokemonAbilities")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ability, container, false)
    }

    override fun sendDataToFragment(data: PokemonDetails) {
        Log.d("CheckData", "sendDataToFragment $data")
        pokemonAbilities = data.abilities
    }

}