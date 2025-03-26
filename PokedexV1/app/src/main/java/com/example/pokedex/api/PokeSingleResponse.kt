package com.example.pokedex.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokeSingleResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int, // Altura en decímetros
    @SerializedName("weight") val weight: Int, // Peso en hectogramos
    @SerializedName("base_experience") val baseExperience: Int,
    @SerializedName("types") val types: List<PokemonType>,
    @SerializedName("sprites") val sprites: PokemonSprites,
    @SerializedName("moves") val moves: List<PokemonMove>
) : Parcelable

@Parcelize
data class PokemonType(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: Typename
) : Parcelable

@Parcelize
data class Typename(
    @SerializedName("name") val name: String
) : Parcelable

@Parcelize
data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String
) : Parcelable

@Parcelize
data class PokemonMove(
    @SerializedName("move") val move: PokemonMoveData
) : Parcelable

@Parcelize
data class PokemonMoveData(
    @SerializedName("name") val name: String
) : Parcelable
