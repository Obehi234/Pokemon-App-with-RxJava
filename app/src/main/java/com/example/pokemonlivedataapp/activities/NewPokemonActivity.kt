package com.example.pokemonlivedataapp.activities


import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.fragment.*
import com.example.pokemonlivedataapp.model.details.Ability
import com.example.pokemonlivedataapp.model.details.PokemonDetails
import com.example.pokemonlivedataapp.repository.PokemonRepository
import com.example.pokemonlivedataapp.viewpager.MyViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.disposables.CompositeDisposable


class NewPokemonActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var imageView: ImageView
    private lateinit var pokemonName: TextView
    private lateinit var pokemonNumber: TextView

    private var abilityFragment = AbilityFragment()
    private val aboutFragment = AboutFragment()
    private var movesFragment = MovesFragment()
    private var statsFragment = StatsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pokemon)

        pokemonName = findViewById(R.id.pokemonName)
        imageView = findViewById(R.id.pokemonImage)
        pokemonNumber = findViewById(R.id.pokemonNumber)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.card_view_pager)


        //get pokemon name from intent
        val selectedPokemonName = intent.getStringExtra("pokemonName")

        pokemonName.text = selectedPokemonName
        if (selectedPokemonName != null) {
            fetchPokemonDetails(selectedPokemonName)
        }

        viewpagerLogic()
    }

    private fun viewpagerLogic() {
        //initialize pager adapter and create fragment instances
        val pagerAdapter = MyViewPagerAdapter(this)
        pagerAdapter.addFragment(aboutFragment)
        pagerAdapter.addFragment(abilityFragment)
        pagerAdapter.addFragment(movesFragment)
        pagerAdapter.addFragment(statsFragment)

        viewPager.adapter = pagerAdapter


        viewPager.setCurrentItem(0, false)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Form"
                1 -> tab.text = "Abilities"
                2 -> tab.text = "Moves"
                3 -> tab.text = "Stats"
            }
        }.attach()
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

    private fun displayPokemonDetails(pokemonDetails: PokemonDetails?) {
        pokemonDetails?.let { details ->
            val pokemonImage = details.sprites.back_default

            Glide.with(this)
                .load(pokemonImage) // Replace with the actual URL property of your PokemonDetails object
                .into(imageView)

            //send pokemon details to respective fragments
            abilityFragment.sendDataToFragment(pokemonDetails)
            movesFragment.sendDataToFragment(pokemonDetails)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}

interface DataListener {
    fun sendDataToFragment(data: PokemonDetails)
}