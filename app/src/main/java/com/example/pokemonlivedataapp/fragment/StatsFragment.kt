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
import com.example.pokemonlivedataapp.adapter.StatsAdapter
import com.example.pokemonlivedataapp.model.details.PokemonDetails
import com.example.pokemonlivedataapp.model.details.Stat
import com.example.pokemonlivedataapp.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class StatsFragment : Fragment(), DataListener {
    private lateinit var statsRecycler: RecyclerView
    private lateinit var statsAdapter: StatsAdapter
    private lateinit var statsList: List<Stat>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_stats, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        statsRecycler = view.findViewById(R.id.rvStats)
        statsAdapter = StatsAdapter(emptyList())
        statsRecycler.adapter = statsAdapter

        try {
            statsAdapter.updateData(statsList)
        }catch (e: Exception) {
            Log.d("CheckData", "${e.message}")
        }

        Log.d("CheckData", "pokemonDetails: ${statsList}")
    }

    override fun sendDataToFragment(data: PokemonDetails) {
        Log.d("CheckData", "sendDataToFragment $data")
        statsList = data.stats
    }
}
