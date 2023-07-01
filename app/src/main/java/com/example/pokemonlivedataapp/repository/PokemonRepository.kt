package com.example.pokemonlivedataapp.repository

import com.example.pokemonlivedataapp.api.ApiService
import com.example.pokemonlivedataapp.api.RetrofitInstance
import com.example.pokemonlivedataapp.model.PokemonResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object PokemonRepository {

    private val apiService : ApiService

    init {
        val retrofit = RetrofitInstance.getInstance()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun getPokemonData() : Observable<PokemonResponse> {
        return apiService.getPokemon()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}