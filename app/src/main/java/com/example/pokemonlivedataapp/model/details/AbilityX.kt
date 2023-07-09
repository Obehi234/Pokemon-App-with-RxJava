package com.example.pokemonlivedataapp.model.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AbilityX(
    val name: String,
    val url: String
): Parcelable