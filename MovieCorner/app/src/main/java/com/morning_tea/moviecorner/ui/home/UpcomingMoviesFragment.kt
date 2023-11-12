package com.morning_tea.moviecorner.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import coil.load
import com.morning_tea.moviecorner.R
import com.morning_tea.moviecorner.databinding.FragmentUpcomingMoviesBinding
import com.morning_tea.moviecorner.ui.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException

@AndroidEntryPoint
class UpcomingMoviesFragment : Fragment(R.layout.fragment_upcoming_movies) {

    private lateinit var binding: FragmentUpcomingMoviesBinding

    private val moviesViewModel: MoviesViewModel by viewModels()

    private val moviesAdapter: MoviesAdapter by lazy {
        val adapter = MoviesAdapter(onItemSelected =  {
            moviesViewModel.onItemSelected(it)
        }, onClick = {
            val navDirections = HomePageFragmentDirections.actionHomePageFragmentToDetailPageFragment(it)
            findNavController().navigate(navDirections)
        })
        adapter.addLoadStateListener {
            when(val state = it.refresh) {
                is LoadState.Error -> {
                    binding.progressCircular.isVisible = false
                    binding.noResult.isVisible = moviesAdapter.itemCount < 1
                    when(state.error) {
                        is UnknownHostException -> {
                            Log.e("TAG", "Unknown Host: ")
                        }
                        else -> {
                            Log.e("TAG", "Error: ${state.error.message}")
                        }
                    }
                }
                is LoadState.Loading -> {
                    binding.noResult.isVisible = false
                    binding.progressCircular.isVisible = true
                }
                else -> {
                    binding.noResult.isVisible = moviesAdapter.itemCount < 1
                    binding.progressCircular.isVisible = false
                }
            }
        }
        adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentUpcomingMoviesBinding.bind(view)
        binding.apply {
            upcomingMovies.adapter = moviesAdapter
            imageNoResult.load(R.drawable.undraw_netflix_q_00_o)
            imageNoResult.load(R.drawable.undraw_netflix_q_00_o)
            btnRetry.setOnClickListener {
                moviesViewModel.getPopularMovies()
            }
        }

        moviesViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            moviesAdapter.submitData(lifecycle, it)
        }

        moviesViewModel.getUpcomingMovies()
    }

}