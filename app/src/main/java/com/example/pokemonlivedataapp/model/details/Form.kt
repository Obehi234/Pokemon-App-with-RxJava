package com.example.pokemonlivedataapp.model.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Form(
    val name: String,
    val url: String
): Parcelable