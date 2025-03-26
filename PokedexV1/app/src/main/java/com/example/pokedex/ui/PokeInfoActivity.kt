package com.example.pokedex.ui
import com.example.pokedex.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pokedex.api.PokeSingleResponse
import com.example.pokedex.databinding.ActivityPokeInfoBinding

class PokeInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        val pokemon: PokeSingleResponse? = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("pokemon_detail", PokeSingleResponse::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("pokemon_detail")
        }

        pokemon?.let { poke ->
            val typeNames = poke.types.map { it.type.name }
            val moves = poke.moves.take(4).joinToString { it.move.name }

            binding.nameTextView.text = poke.name
            binding.heightText.text = getString(R.string.height_format, poke.height / 10.0)
            binding.weightText.text = getString(R.string.weight_format, poke.weight / 10.0)
            binding.typeText.text = getString(R.string.type_format, typeNames.joinToString())           //${poke.id}
            binding.movesText.text= getString(R.string.moves_format, moves)

            val highResImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${poke.id}.png"

            Glide.with(this)
                .load(highResImageUrl)
                .into(binding.imageView)
        }
    }


}
