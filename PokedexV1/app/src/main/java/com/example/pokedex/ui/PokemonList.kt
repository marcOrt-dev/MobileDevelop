package com.example.pokedex.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.databinding.ActivityMainBinding
import com.example.pokedex.model.PokeListAdapter
import com.example.pokedex.model.PokeListViewModel


class PokemonList : AppCompatActivity() {

    private lateinit var viewModel: PokeListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PokeListViewModel::class.java]

        initUI()
        observeViewModel()
    }

    private fun initUI() {
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getCategories()
    }

    private fun observeViewModel() {
        viewModel.categories.observe(this, Observer { categories ->
            CategoryAdapter(categories) { pokemonId ->
                val intent = Intent(this, PokeInfoActivity::class.java)
                intent.putExtra("pokemon_detail", pokemonId)
                startActivity(intent)
            }.also { binding.categoryRecyclerView.adapter = it }
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            // Mostrar el error en pantalla
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        })
    }
}
