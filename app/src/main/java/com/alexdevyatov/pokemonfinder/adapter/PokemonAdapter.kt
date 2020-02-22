package com.alexdevyatov.pokemonfinder.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexdevyatov.pokemonfinder.R
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(val pokemons : List<Pokemon>,
                     val context: Context) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false))

    override fun getItemCount(): Int = pokemons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.tvPokemonName.text = pokemon.name
        Glide.with(context).load(pokemon.sprites.front).centerCrop().into(holder.ivPokemon)
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val ivPokemon = view.ivPokemon
        val tvPokemonName = view.tvPokemonName
    }
}