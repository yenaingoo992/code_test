package com.morning_tea.moviecorner.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.morning_tea.moviecorner.R
import com.morning_tea.moviecorner.databinding.FragmentMovieDetailBinding
import com.morning_tea.moviecorner.ui.viewmodel.MoviesViewModel
import com.morning_tea.moviecorner.utils.formatDecimalString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPageFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args: DetailPageFragmentArgs by navArgs()

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMovieDetailBinding.bind(view)
        val movie = args.movie
        binding.apply {
            this.movie = movie
            rating.text = movie.voteAverage.formatDecimalString()
            poster.load(movie.imageUrl) {
                crossfade(true)
                error(R.drawable.no_photo)
            }
            imgFav.setColorFilter(if (movie.isFavourite) Color.RED else Color.GRAY)
            imgFav.setOnClickListener {
                movie.isFavourite = !movie.isFavourite
                moviesViewModel.onItemSelected(movie)
                imgFav.setColorFilter(if (movie.isFavourite) Color.RED else Color.GRAY)
            }
        }
    }
}