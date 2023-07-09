package com.example.pokemonlivedataapp.model.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Type(
    val slot: Int,
    val type: TypeX
): Parcelable