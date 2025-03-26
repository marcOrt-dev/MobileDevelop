package com.example.pokedex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.model.Category

class CategoryAdapter(
    private val categories: List<Category>,
    private val onPokemonClick: (Int) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val categoryNameTextView: TextView = view.findViewById(R.id.categoryNameTextView)
        private val pokemonRecyclerView: RecyclerView = view.findViewById(R.id.pokemonRecyclerView)

        fun bind(category: Category) {
            categoryNameTextView.text = category.name

            // Configurar el RecyclerView interno
            pokemonRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            pokemonRecyclerView.adapter = PokemonAdapter(category.pokemons, onPokemonClick)
            // No es necesario llamar a notifyDataSetChanged() aquí
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}
