package com.example.pokemonlivedataapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.adapter.AbilityAdapter
import com.example.pokemonlivedataapp.model.details.Ability

class AbilityFragment : Fragment() {

    private lateinit var abilityRecycler: RecyclerView
    private lateinit var abilityAdapter: AbilityAdapter

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
        return view
    }

    fun setData(abilityList: List<Ability>) {
        abilityAdapter.abilityList = abilityList
        abilityAdapter.notifyDataSetChanged()
    }
}
