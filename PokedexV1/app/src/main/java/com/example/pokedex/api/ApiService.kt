package com.example.pokedex.api

import com.example.pokedex.model.PokeTypeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon/{id}")
    suspend fun getPokemonInfo(@Path("id") id: Int): Pokemon

    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): PokeApiResponse

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): Pokemon

    @GET("pokemon/{name}")
    suspend fun searchPokemonByName(@Path("name") name: String): PokeSingleResponse

    // Nueva funcion para filtrar Pokemons por clase
    @GET("type/{type}")
    suspend fun getPokemonByType(@Path("type") type: String): PokeTypeResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") pokemonId: Int): PokeSingleResponse
}
