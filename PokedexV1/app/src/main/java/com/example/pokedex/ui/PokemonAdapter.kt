package com.example.pokedex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.api.Pokemon

class PokemonAdapter(
    private val pokemons: List<Pokemon>,
    private val onPokemonClick: (Int) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val pokemonTextView: TextView = view.findViewById(R.id.pokemonTextView)
        private val pokemonImageView: ImageView = view.findViewById(R.id.pokemonImageView)

        fun bind(pokemon: Pokemon) {
            pokemonTextView.text = pokemon.name

            // Cargar imagen del Pokémon
            Glide.with(itemView.context)
                .load(pokemon.highResImageUrl)
                .into(pokemonImageView)

            itemView.setOnClickListener {
                onPokemonClick(pokemon.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int = pokemons.size
}
