package com.example.pokemonlivedataapp.model.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonDetails(
    val abilities: List<Ability>,
    val forms: List<Form>,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,

): Parcelable