package com.alexdevyatov.pokemonfinder.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.alexdevyatov.pokemonfinder.App
import com.alexdevyatov.pokemonfinder.R
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.viewmodel.PokemonViewModel
import com.alexdevyatov.pokemonfinder.viewmodel.factory.PokemonViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.random.Random

class MainFragment : Fragment() {

    private val MAX_POKEMON_ID = 807

    private var btnSearch : Button? = null
    private var btnRandom : Button? = null
    private var btnFavorites : Button? = null

    private var pokemonViewModel: PokemonViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity!!.application as App).getAppComponent()
        pokemonViewModel = ViewModelProviders.of(this, PokemonViewModelFactory(appComponent!!)).get(PokemonViewModel::class.java)
        pokemonViewModel!!.data.observe(this, Observer<Pokemon> { showPokemon(it)})
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        btnSearch = view.findViewById(R.id.btnSearch)
        btnRandom = view.findViewById(R.id.btnRandom)
        btnFavorites = view.findViewById(R.id.btnFavorites)
        btnSearch!!.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchPokemonFragment())
        }
        btnRandom!!.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val pokemonId = generateRandomPokemonId()
            pokemonViewModel!!.id = pokemonId
        }
        btnFavorites!!.setOnClickListener {
            it.findNavController().navigate(MainFragmentDirections.actionMainFragmentToFavoritesFragment())
        }
        return view
    }

    private fun showPokemon(pokemon: Pokemon?) {
        progressBar.visibility = View.VISIBLE
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchPokemonFragment(pokemon))
    }

    private fun generateRandomPokemonId() = Random.nextInt(1, MAX_POKEMON_ID + 1)

}
