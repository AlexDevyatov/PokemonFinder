package com.alexdevyatov.pokemonfinder.view.fragment


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alexdevyatov.pokemonfinder.App
import com.alexdevyatov.pokemonfinder.R
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.viewmodel.PokemonViewModel
import com.alexdevyatov.pokemonfinder.viewmodel.factory.PokemonViewModelFactory
import kotlinx.android.synthetic.main.fragment_search_pokemon.*


class SearchPokemonFragment : Fragment() {

    private var searchView: SearchView? = null
    private var queryTextListener: SearchView.OnQueryTextListener? = null
    private var pokemonViewModel: PokemonViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_pokemon, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        createViewModel()
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
                    pokemonViewModel!!.request()
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
        textview.text = pokemon!!.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> return false
        }
        searchView!!.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }

    fun createViewModel() {
        val appComponent = (activity!!.application as App).getAppComponent()

        pokemonViewModel = ViewModelProviders.of(this, PokemonViewModelFactory(appComponent!!)).get(PokemonViewModel::class.java)
    }
}
