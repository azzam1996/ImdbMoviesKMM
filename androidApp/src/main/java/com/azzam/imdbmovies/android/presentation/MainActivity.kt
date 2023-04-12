package com.azzam.imdbmovies.android.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.azzam.imdbmovies.android.R
import com.azzam.imdbmovies.android.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController: NavController
        get() = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ImdbMovies);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setupWithNavController(navController)
    }
}

