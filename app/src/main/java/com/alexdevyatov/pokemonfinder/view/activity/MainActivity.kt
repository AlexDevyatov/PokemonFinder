package com.alexdevyatov.pokemonfinder.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexdevyatov.pokemonfinder.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
