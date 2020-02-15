package com.alexdevyatov.pokemonfinder.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexdevyatov.pokemonfinder.R
import com.alexdevyatov.pokemonfinder.view.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, MainFragment()).commit()
    }
}
