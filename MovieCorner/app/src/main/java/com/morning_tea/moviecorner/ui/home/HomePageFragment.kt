package com.morning_tea.moviecorner.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.morning_tea.moviecorner.R
import com.morning_tea.moviecorner.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private lateinit var binding: FragmentHomePageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomePageBinding.bind(view)
        val pagerAdapter = MoviesPagerAdapter(this)
        binding.apply {
            moviePager.adapter = pagerAdapter
            TabLayoutMediator(movieTabs, moviePager) { tab, position ->
                tab.text = if (position == 0) {
                    "Now Popular"
                } else {
                    "The Upcoming"
                }
            }.attach()
        }
    }

    private inner class MoviesPagerAdapter(fragment: Fragment) :
        FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return if (position == 0) {
                PopularMoviesFragment()
            } else {
                UpcomingMoviesFragment()
            }
        }
    }
}