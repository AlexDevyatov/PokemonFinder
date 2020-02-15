package com.alexdevyatov.pokemonfinder.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.alexdevyatov.pokemonfinder.R
import com.alexdevyatov.pokemonfinder.view.fragment.MainFragment
import com.alexdevyatov.pokemonfinder.view.fragment.MainFragmentDirections
import com.alexdevyatov.pokemonfinder.view.fragment.SearchPokemonFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
