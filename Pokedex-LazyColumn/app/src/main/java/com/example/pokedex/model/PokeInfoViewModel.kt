package com.example.pokedex.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.Pokemon
import com.example.pokedex.api.PokemonType
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class PokeInfoViewModel : ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonDescription = MutableLiveData<Pokemon>()
    val pokemonType = MutableLiveData<PokemonType>()  // Nueva LiveData para el tipo de Pokémon

    fun getPokemonInfo(id: Int) {
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                    getPokemonType(pokemon.types.firstOrNull()?.type?.name)  // Llamar a getPokemonType()
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
        })
    }

    fun getPokemonDescription(id: Int) {
        val callDescription = service.getPokemonSpecies(id)
        callDescription.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonDescription.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
        })
    }

    private fun getPokemonType(typeName: String?) {
        if (typeName == null) return

        val callType = service.getPokemonType(typeName)
        callType.enqueue(object : Callback<PokemonType> {
            override fun onResponse(call: Call<PokemonType>, response: Response<PokemonType>) {
                response.body()?.let { type ->
                    pokemonType.postValue(type)
                }
            }

            override fun onFailure(call: Call<PokemonType>, t: Throwable) {
                call.cancel()
            }
        })
    }
}
