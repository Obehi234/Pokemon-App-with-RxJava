package com.example.pokemonlivedataapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.adapter.StatsAdapter
import com.example.pokemonlivedataapp.model.details.Stat

class StatsFragment : Fragment() {
    private lateinit var statsRecycler: RecyclerView
    private lateinit var statsAdapter: StatsAdapter
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

    fun setData(statsList: List<Stat>) {
        statsAdapter.statsList = statsList
        statsAdapter.notifyDataSetChanged()



    }
}
