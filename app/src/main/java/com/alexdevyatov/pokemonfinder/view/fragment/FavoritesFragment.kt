package com.alexdevyatov.pokemonfinder.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexdevyatov.pokemonfinder.App

import com.alexdevyatov.pokemonfinder.R
import com.alexdevyatov.pokemonfinder.adapter.PokemonAdapter
import com.alexdevyatov.pokemonfinder.createPokemonList
import com.alexdevyatov.pokemonfinder.database.AppDatabase
import com.alexdevyatov.pokemonfinder.model.Pokemon
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    private var pokemons: List<Pokemon> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Observable.fromCallable {
            val db = (activity!!.application as App).getDatabase()
            pokemons = (db as AppDatabase).pokemonDao().loadAllPokemons().createPokemonList()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (pokemons.isNotEmpty()) {
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = PokemonAdapter(pokemons, activity!!)
                    tvEmptyPokemons.visibility = View.GONE
                } else {
                    tvEmptyPokemons.visibility = View.VISIBLE
                }
            }
    }


}
