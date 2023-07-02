package com.example.pokemonlivedataapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.repository.PokemonRepository
import io.reactivex.disposables.CompositeDisposable

class NewPokemonActivity : AppCompatActivity() {
    private val compositeDisposable =  CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pokemon)

        val selectedPokemonName = intent.getStringExtra("pokemonName")

        if (selectedPokemonName != null) {
            fetchPokemonDetails(selectedPokemonName)
        }
    }

    private fun fetchPokemonDetails(pokemonName: String) {
        val disposable = PokemonRepository.getPokemonDetails(pokemonName)
            .subscribe(
                { pokemonDetails ->


                },
                { error ->

                }
            )
        compositeDisposable.add(disposable)

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}