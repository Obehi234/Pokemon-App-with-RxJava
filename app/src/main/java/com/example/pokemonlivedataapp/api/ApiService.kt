package com.example.pokemonlivedataapp.api

import io.reactivex.Observable
import com.example.pokemonlivedataapp.model.PokemonResponse
import com.example.pokemonlivedataapp.model.details.PokemonDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/")
    fun getPokemon(): Observable<PokemonResponse>

    @GET("pokemon/{name}")
    fun getPokemonDetails(@Path("name")name: String) : Observable<PokemonDetails>
}