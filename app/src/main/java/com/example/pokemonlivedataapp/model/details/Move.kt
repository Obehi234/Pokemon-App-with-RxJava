package com.example.pokemonlivedataapp.model.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Move(
    val move: MoveX,
//    val version_group_details: List<VersionGroupDetail>
): Parcelable