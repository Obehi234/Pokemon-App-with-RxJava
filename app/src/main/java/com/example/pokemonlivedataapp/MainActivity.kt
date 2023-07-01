package com.example.pokemonlivedataapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.adapter.PokemonAdapter
import com.example.pokemonlivedataapp.repository.PokemonRepository

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        pokemonAdapter = PokemonAdapter(emptyList())
        recyclerView.adapter = pokemonAdapter

        showProgressbar()
        PokemonRepository.getPokemonData()
            .subscribe(
                {response ->
                    hideProgressBar()
                Log.d("Network Call", "Network Call Successful : $response")
                    pokemonAdapter.updateData(response.results)
                },
                {error ->
                    Log.d("NetworkCall", "Failed: $error")
                }
            )

    }

    private fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}