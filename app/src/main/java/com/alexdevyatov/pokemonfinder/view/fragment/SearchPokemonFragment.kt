package com.alexdevyatov.pokemonfinder.view.fragment


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.alexdevyatov.pokemonfinder.*
import com.alexdevyatov.pokemonfinder.model.Pokemon
import com.alexdevyatov.pokemonfinder.viewmodel.PokemonViewModel
import com.alexdevyatov.pokemonfinder.viewmodel.factory.PokemonViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search_pokemon.*
import java.util.*


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
        pokemonViewModel!!.data.observe(
            this@SearchPokemonFragment,
            Observer<Pokemon> { updatePokemonView(it) })
        if (pokemon == null) {
            llTypesContainer.removeAllViews()
        } else {
            updatePokemonView(pokemon)
        }
        val db = (activity!!.application as App).getDatabase()
        ivPokeball.setOnClickListener {
            val selected = ivPokeball.isSelected
            if (!selected) {
                Completable.fromAction {
                    val pokemonEntity = pokemon!!.createEntity()
                    db!!.pokemonDao().insertPokemon(
                        pokemonEntity, pokemon!!.createAbilityEntities(),
                        pokemon!!.createStatEntities(), pokemon!!.createTypeEntities()
                    )
                }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        ivPokeball.isSelected = true
                        Toast.makeText(activity, R.string.like, Toast.LENGTH_SHORT).show()
                    }
            } else {
                Completable.fromAction {
                    val pokemonEntity = pokemon!!.createEntity()
                    db!!.pokemonDao().deletePokemon(pokemonEntity)
                }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        ivPokeball.isSelected = false
                        Toast.makeText(activity, R.string.dismiss, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        createViewModel()
        pokemon = args.pokemon
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
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
                    mainLayout.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                    pokemonViewModel!!.name = query.decapitalize()
                    hideSoftKeyboard()
                    return true
                }
            }
            searchView!!.setOnQueryTextListener(queryTextListener)
            searchView!!.queryHint = getString(R.string.hint)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updatePokemonView(pokemon: Pokemon?) {
        if (pokemon == null) {
            mainLayout.visibility = View.GONE
            progressBar.visibility = View.GONE
            tvPokemonNotFound.visibility = View.VISIBLE
            return
        }
        mainLayout.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        tvPokemonNotFound.visibility = View.GONE

        pokemon.name = pokemon.name.capitalize()
        this.pokemon = pokemon
        var count = 0
        Completable.fromAction {
            val db = (activity!!.application as App).getDatabase()
            count = db!!.pokemonDao().pokemonsCount(pokemon!!.id)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (count > 0) {
                    ivPokeball.isSelected = true
                }
            }


        ivPokeball.visibility = View.VISIBLE
        tvPokemonName.text = pokemon.name
        tvName.text = resources.getString(R.string.name) + " " + pokemon.name
        String.format("%.1f", pokemon.weight * 0.1)
        tvWeight.text = resources.getString(R.string.weight) + " " + String.format(
            "%.1f",
            pokemon.weight * 0.1
        ) + " kg"
        tvHeight.text = resources.getString(R.string.height) + " " + String.format(
            "%.1f",
            pokemon.height * 0.1
        ) + " m"

        llTypesContainer.removeAllViews()
        val tvCaption = TextView(activity)
        tvCaption.text = resources.getString(R.string.types) + " "
        tvCaption.setPadding(0, 5, 0, 5)
        tvCaption.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        tvCaption.setTextColor(ContextCompat.getColor(activity as Context, R.color.textColorBlack))
        llTypesContainer.addView(tvCaption)

        for (type in pokemon.types) {
            val tvType = TextView(activity)
            tvType.text = type.type.name
            tvType.setPadding(5, 5, 5, 5)
            tvType.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            tvType.setTextColor(ContextCompat.getColor(activity as Context, R.color.textColorBlack))
            llTypesContainer.addView(tvType)
        }

        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .signature(ObjectKey(UUID.randomUUID().toString()))
            .override(200, 200)
        Glide.with(this)
            .load(App.POKEMON_IMG_URL + "${pokemon.id}.png")
            .apply(requestOptions)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(ivPokemon)
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

        pokemonViewModel = ViewModelProviders.of(
            this,
            PokemonViewModelFactory(appComponent!!, activity!!.application)
        )
            .get(PokemonViewModel::class.java)
    }

    private fun hideSoftKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm =
                activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
