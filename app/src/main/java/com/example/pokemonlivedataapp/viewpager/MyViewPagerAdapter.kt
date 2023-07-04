package com.example.pokemonlivedataapp.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokemonlivedataapp.adapter.AbilityAdapter
import com.example.pokemonlivedataapp.adapter.FormsRecyclerAdapter
import com.example.pokemonlivedataapp.adapter.MovesAdapter
import com.example.pokemonlivedataapp.adapter.SpeciesAdapter

class MyViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){

        private val fragmentList = mutableListOf<Fragment>()

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]

        }
    }
