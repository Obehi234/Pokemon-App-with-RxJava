package com.example.pokemonlivedataapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonlivedataapp.R
import com.example.pokemonlivedataapp.adapter.PokemonAdapter
import com.example.pokemonlivedataapp.connectivity.NetworkConnectivityData
import com.example.pokemonlivedataapp.repository.PokemonRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var ivInternet: ImageView
    private lateinit var tvInternet: TextView
    private lateinit var connectivityLiveData: NetworkConnectivityData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        ivInternet = findViewById(R.id.ivInternet)
        tvInternet = findViewById(R.id.tvInternet)

        pokemonAdapter = PokemonAdapter(emptyList()) { clickedPokemon ->
            val intent = Intent(this, NewPokemonActivity::class.java)
            intent.putExtra("pokemonName", clickedPokemon.name)
            startActivity(intent)

        }
        recyclerView.adapter = pokemonAdapter

        connectivityLiveData = NetworkConnectivityData(application)

        connectivityLiveData.observe(this) { isConnected ->
            if (isConnected) {
                showProgressbar()
                pokemonNetworkCall()
                ivInternet.visibility = View.GONE
                tvInternet.visibility = View.GONE
            } else {
                ivInternet.visibility = View.VISIBLE
                tvInternet.visibility = View.VISIBLE
                Log.d("InternetCheck", "No Internet")

            }
        }
    }

    private fun pokemonNetworkCall() {
        PokemonRepository.getPokemonData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    hideProgressBar()
                    Log.d("Network Call", "Network Call Successful: $response")

                    pokemonAdapter.updateData(response.results)
                },
                { error ->
                    Log.d("NetworkCall", "Failed: $error")
                }
            )
    }

    private fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

}