package com.example.pokemonlivedataapp.api

import io.reactivex.Observable
import com.example.pokemonlivedataapp.model.PokemonResponse
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon/")
    fun getPokemon(): Observable<PokemonResponse>

}