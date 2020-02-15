package com.alexdevyatov.pokemonfinder.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

import com.alexdevyatov.pokemonfinder.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private var btnSearch : Button? = null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        btnSearch = view.findViewById(R.id.btnSearch)
        btnSearch!!.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToSearchPokemonFragment()
            it.findNavController().navigate(action)
        }
        return view
    }


}
