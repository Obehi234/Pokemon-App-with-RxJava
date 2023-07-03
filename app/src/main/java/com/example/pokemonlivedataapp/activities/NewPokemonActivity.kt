package com.example.pokemonlivedataapp.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.fragment.AbilityFragment
import com.example.pokemonlivedataapp.fragment.AboutFragment
import com.example.pokemonlivedataapp.fragment.MovesFragment
import com.example.pokemonlivedataapp.fragment.StatsFragment
import com.example.pokemonlivedataapp.model.details.PokemonDetails
import com.example.pokemonlivedataapp.repository.PokemonRepository
import com.example.pokemonlivedataapp.viewpager.MyPagerAdapter
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.disposables.CompositeDisposable

class NewPokemonActivity : AppCompatActivity() {
    private val compositeDisposable =  CompositeDisposable()
    private lateinit var flexboxLayout :FlexboxLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var imageView: ImageView
    private lateinit var pokemonName: TextView
    private lateinit var pokemonNumber : TextView
    val adapter = MyPagerAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pokemon)

        pokemonName = findViewById(R.id.pokemonName)
        imageView= findViewById(R.id.pokemonImage)
        pokemonNumber = findViewById(R.id.pokemonNumber)

        flexboxLayout = findViewById(R.id.flexboxLayout)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.card_view_pager)

        val ability = AbilityFragment()
        val aboutFragment = AboutFragment()
        val moves = MovesFragment()
        val stats = StatsFragment()
        adapter.addFragment(ability)
        adapter.addFragment(aboutFragment)
        adapter.addFragment(moves)
        adapter.addFragment(stats)

        val selectedPokemonName = intent.getStringExtra("pokemonName")

        if (selectedPokemonName != null) {
            fetchPokemonDetails(selectedPokemonName)
        }

        pokemonName.text = selectedPokemonName
        viewPager.adapter = adapter

        viewPager.setCurrentItem(0,false)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Forms"
                1 -> tab.text = "Abilities"
                2 -> tab.text = "Moves"
                3 -> tab.text = "Stats"
            }
        }.attach()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val fragment = when (position) {
                    0 -> AboutFragment()
                    1 -> AbilityFragment()
                    2 -> MovesFragment()
                    3 -> StatsFragment()
                    else -> throw IllegalArgumentException("Invalid fragment position: $position")
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit()
            }
        })


    }


    private fun fetchPokemonDetails(pokemonName: String) {
        val disposable = PokemonRepository.getPokemonDetails(pokemonName)
            .subscribe(
                { pokemonDetails ->

                    displayPokemonDetails(pokemonDetails)
                },
                { error ->
                    showErrorToast()
                }
            )
        compositeDisposable.add(disposable)

    }

    private fun showErrorToast() {
        Toast.makeText(this, "Failed to fetch Pokemon details", Toast.LENGTH_LONG).show()
    }

    private fun displayPokemonDetails(pokemonDetails: PokemonDetails?) {pokemonDetails?.let { details ->
        val pokemonImage = details.sprites.back_default
        Glide.with(this)
            .load(pokemonImage) // Replace with the actual URL property of your PokemonDetails object
            .into(imageView)

    }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}