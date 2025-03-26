package com.example.pokedex.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.Pokemon
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeInfoViewModel : ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: ApiService = retrofit.create(ApiService::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()
    val pokemonDescription = MutableLiveData<Pokemon>()
    val errorMessage = MutableLiveData<String>()  // Nuevo LiveData para manejar errores

    fun getPokemonInfo(id: Int) {
        viewModelScope.launch {
            try {
                val response = service.getPokemonInfo(id)

                if (response != null) {
                    pokemonInfo.postValue(response)  // Actualiza el valor de pokemonInfo
                } else {
                    errorMessage.postValue("No se encontraron datos para el Pokémon.")
                }

            } catch (e: Exception) {
                // Captura de errores y mensajes detallados
                handleError(e)
            }
        }
    }

    fun getPokemonDescription(id: Int) {
        viewModelScope.launch {
            try {
                val response = service.getPokemonSpecies(id)

                if (response != null) {
                    pokemonDescription.postValue(response)  // Actualiza el valor de pokemonDescription
                } else {
                    errorMessage.postValue("No se encontró la descripción del Pokémon.")
                }

            } catch (e: Exception) {
                handleError(e)
            }
        }
    }

    private fun handleError(e: Exception) {
        // Aquí puedes agregar más casos dependiendo de la excepción
        when (e) {
            is java.net.SocketTimeoutException -> {
                errorMessage.postValue("Tiempo de espera agotado. Por favor, intenta nuevamente.")
            }
            is retrofit2.HttpException -> {
                val errorCode = e.code()
                errorMessage.postValue("Error HTTP $errorCode: ${e.message()}")
            }
            is java.io.IOException -> {
                errorMessage.postValue("Error de red. Verifica tu conexión a Internet.")
            }
            else -> {
                errorMessage.postValue("Ocurrió un error inesperado: ${e.message}")
            }
        }
    }
}
