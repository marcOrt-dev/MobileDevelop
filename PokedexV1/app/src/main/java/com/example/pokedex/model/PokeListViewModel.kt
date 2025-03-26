package com.example.pokedex.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.Pokemon
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.Species
import com.example.pokedex.api.Sprites
import java.util.Locale

class PokeListViewModel : ViewModel() {
    val errorMessage = MutableLiveData<String>()

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private val pokeApiService: ApiService = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    fun getCategories() {
        val categoryList = mutableListOf<Category>()
        val typeNames = listOf("fire", "water", "grass", "electric")

        typeNames.forEach { type ->
            viewModelScope.launch {
                try {
                    Log.d("PokeListViewModel", "Haciendo solicitud para el tipo: $type")
                    val response = pokeApiService.getPokemonByType(type)

                    // Log para verificar la respuesta
                    Log.d("PokeListViewModel", "Respuesta exitosa para el tipo: $type")

                    response.pokemon.map { pokeEntry ->
                        val pokeId = extractIdFromUrl(pokeEntry.pokemon.url)
                        val pokeName = pokeEntry.pokemon.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                        }

                        Pokemon(
                            pokeId,
                            pokeName,
                            baseExperience = 0,
                            height = 0,
                            isDefault = true,
                            order = 0,
                            weight = 0,
                            sprites = Sprites("", "", "", "", "", "", "", ""),
                            abilities = listOf(),
                            forms = listOf(),
                            gameIndices = listOf(),
                            heldItems = listOf(),
                            locationAreaEncounters = "",
                            moves = listOf(),
                            species = Species("", ""),
                            stats = listOf(),
                            types = listOf(),
                            flavorTextEntries = listOf(),
                            spanishFlavorTextEntries = listOf()
                        )
                    }.let { pokemonList ->
                        categoryList.add(Category(type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }, pokemonList))
                    }
                    Log.d("PokeListViewModel", "Categorías cargadas: ${categoryList.size} - Contenido: $categoryList")
                    _categories.postValue(categoryList)
                } catch (e: Exception) {
                    Log.e("PokeListViewModel", "Error obteniendo datos para el tipo: $type - ${e.message}")
                }
            }
        }
    }

    private fun extractIdFromUrl(url: String): Int {
        return url.trimEnd('/').split('/').last().toInt()
    }
}

// Clase Category para almacenar los tipos y sus Pokémon
data class Category(val name: String, val pokemons: List<Pokemon>)
