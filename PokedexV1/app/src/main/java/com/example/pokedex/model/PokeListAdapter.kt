package com.example.pokedex.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.api.PokeResult
import com.example.pokedex.databinding.PokeListBinding

class PokeListAdapter(private val pokemonClick: (Int) -> Unit) :
    RecyclerView.Adapter<PokeListAdapter.SearchViewHolder>() {

    private var pokemonList: List<PokeResult> = emptyList()

    fun setData(list: List<PokeResult>) {
        pokemonList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = PokeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    private fun extractIdFromUrl(url: String): Int {
        val parts = url.trimEnd('/').split("/")
        return parts.last().toInt()
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val binding = holder.binding
        val pokemon = pokemonList[position]

        val id = extractIdFromUrl(pokemon.url)
        binding.pokemonText.text = "#$id - ${pokemon.name.replaceFirstChar { it.uppercase() }}"

        // Generar la URL del sprite
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

        // Cargar la imagen y ajustarla a las dimensiones del TextView
        Glide.with(binding.root.context)
            .load(imageUrl)
            .placeholder(R.drawable.pokedex_app_background)  // Imagen predeterminada
            .error(R.drawable.pokedex)  // Imagen si hay un error
            .fitCenter()
            .into(binding.pokemonImageView)

        holder.itemView.setOnClickListener {
            pokemonClick(id)
        }
    }

    class SearchViewHolder(val binding: PokeListBinding) :
        RecyclerView.ViewHolder(binding.root)
}

