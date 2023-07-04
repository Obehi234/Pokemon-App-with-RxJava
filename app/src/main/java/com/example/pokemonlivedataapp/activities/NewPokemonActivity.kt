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
import com.example.pokemonlivedataapp.viewpager.MyViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.disposables.CompositeDisposable


class NewPokemonActivity : AppCompatActivity() {
    private val compositeDisposable =  CompositeDisposable()
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var imageView: ImageView
    private lateinit var pokemonName: TextView
    private lateinit var pokemonNumber : TextView

    private var abilityFragment = AbilityFragment()
    private val aboutFragment = AboutFragment()
    private var movesFragment = MovesFragment()
    private var statsFragment = StatsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_pokemon)

        pokemonName = findViewById(R.id.pokemonName)
        imageView= findViewById(R.id.pokemonImage)
        pokemonNumber = findViewById(R.id.pokemonNumber)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.card_view_pager)



        val pagerAdapter = MyViewPagerAdapter(this)
        pagerAdapter.addFragment(abilityFragment)
        pagerAdapter.addFragment(aboutFragment)
        pagerAdapter.addFragment(movesFragment)
        pagerAdapter.addFragment(statsFragment)

        viewPager.adapter = pagerAdapter

        val selectedPokemonName = intent.getStringExtra("pokemonName")

        if (selectedPokemonName != null) {
            fetchPokemonDetails(selectedPokemonName)
            abilityFragment = AbilityFragment.newInstance(selectedPokemonName)
            movesFragment = MovesFragment.newInstance(selectedPokemonName)
            statsFragment = StatsFragment.newInstance(selectedPokemonName)
        }

        pokemonName.text = selectedPokemonName


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
                    0 -> aboutFragment
                    1 -> abilityFragment
                    2 -> movesFragment
                    3 -> statsFragment
                    else -> throw IllegalArgumentException("Invalid fragment position: $position")
                }

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