package com.example.pokemonlivedataapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.adapter.FormsRecyclerAdapter
import com.example.pokemonlivedataapp.adapter.SpeciesAdapter
import com.example.pokemonlivedataapp.model.PokemonResponse
import com.example.pokemonlivedataapp.model.details.Form
import com.example.pokemonlivedataapp.model.details.PokemonDetails
import com.example.pokemonlivedataapp.model.details.Species
import com.example.pokemonlivedataapp.repository.PokemonRepository.getPokemonDetails

class AboutFragment : Fragment() {
   private lateinit var formsRecyclerView: RecyclerView
   private lateinit var speciesRecyclerView: RecyclerView
   private lateinit var formsAdapter: FormsRecyclerAdapter
   private lateinit var speciesAdapter: SpeciesAdapter

   private val formsList = mutableListOf<Form>()
   private val speciesList = mutableListOf<Species>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_about, container, false)

        formsRecyclerView = rootView.findViewById(R.id.rvForms)
        speciesRecyclerView = rootView.findViewById(R.id.rvSpecies)
        return rootView
    }

    fun setData(formsList: List<Form>, speciesList: List<Species>) {
        formsAdapter = FormsRecyclerAdapter(formsList)
        speciesAdapter = SpeciesAdapter(speciesList)

        formsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        speciesRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        formsRecyclerView.adapter = formsAdapter
        speciesRecyclerView.adapter = speciesAdapter


    }





}