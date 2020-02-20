package com.alexdevyatov.pokemonfinder.view.fragment


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.alexdevyatov.pokemonfinder.App
import com.alexdevyatov.pokemonfinder.R
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.viewmodel.PokemonViewModel
import com.alexdevyatov.pokemonfinder.viewmodel.factory.PokemonViewModelFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_search_pokemon.*


class SearchPokemonFragment : Fragment() {

    val args: SearchPokemonFragmentArgs by navArgs()

    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null
    private var pokemonViewModel: PokemonViewModel? = null

    private var pokemon: Pokemon? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_pokemon, container, false)
    }

    override fun onStart() {
        super.onStart()
        if (pokemon == null) {
            llTypesContainer.removeAllViews()
        } else {
            updatePokemonView(pokemon)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        createViewModel()
        pokemon = args.pokemon
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))

            queryTextListener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    Log.i("onQueryTextChange", newText)

                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    Log.i("onQueryTextSubmit", query)
                    pokemonViewModel!!.name = query
                    pokemonViewModel!!.data.observe(this@SearchPokemonFragment, Observer<Pokemon> { updatePokemonView(it)})
                    return true
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
            searchView!!.queryHint = "Найти покемона по имени"
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updatePokemonView(pokemon: Pokemon?) {
        tvPokemonName.text = pokemon!!.name
        tvName.text = resources.getString(R.string.name) + " " + pokemon!!.name
        String.format("%.1f", pokemon.weight * 0.1)
        tvWeight.text = resources.getString(R.string.weight) + " " + String.format("%.1f", pokemon.weight * 0.1) + " kg"
        tvHeight.text = resources.getString(R.string.height) + " " + String.format("%.1f", pokemon.height * 0.1) + " m"

        llTypesContainer.removeAllViews()
        val tvCaption = TextView(activity)
        tvCaption.text = resources.getString(R.string.types) + " "
        tvCaption.setPadding(0,5,0,5)
        tvCaption.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        tvCaption.setTextColor(ContextCompat.getColor(activity as Context, R.color.textColorBlack))
        llTypesContainer.addView(tvCaption)

        for (type in pokemon.types) {
            val tvType = TextView(activity)
            tvType.text = type.type.name
            tvType.setPadding(5,5,5,5)
            tvType.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            tvType.setTextColor(ContextCompat.getColor(activity as Context, R.color.textColorBlack))
            llTypesContainer.addView(tvType)
        }

        Glide.with(this).load(pokemon!!.sprites.front).centerCrop().into(ivPokemon)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> return false
        }
        searchView!!.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }

    private fun createViewModel() {
        val appComponent = (activity!!.application as App).getAppComponent()

        pokemonViewModel = ViewModelProviders.of(this, PokemonViewModelFactory(appComponent!!)).get(PokemonViewModel::class.java)
    }
}
