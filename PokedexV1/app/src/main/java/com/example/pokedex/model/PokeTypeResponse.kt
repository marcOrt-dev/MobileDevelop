package com.example.pokedex.model

import com.example.pokedex.api.PokeResult
import com.google.gson.annotations.SerializedName

data class PokeTypeResponse(
    @SerializedName("pokemon") val pokemon: List<PokemonEntry>
)

data class PokemonEntry(
    @SerializedName("pokemon") val pokemon: PokeResult
)
