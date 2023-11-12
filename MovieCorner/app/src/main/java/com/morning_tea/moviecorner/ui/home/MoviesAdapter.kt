package com.morning_tea.moviecorner.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.morning_tea.moviecorner.R
import com.morning_tea.moviecorner.databinding.ItemMovieCardBinding
import com.morning_tea.moviecorner.domain.model.Movie
import com.morning_tea.moviecorner.utils.formatDecimalString

class MoviesAdapter(val onItemSelected:(Movie) -> Unit, val onClick: (Movie) -> Unit) : PagingDataAdapter<Movie, MoviesAdapter.ItemViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemMovieCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

   inner class ItemViewHolder(private val binding: ItemMovieCardBinding): ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.apply {
                this.movie = movie
                tvRating.text = movie.voteAverage.formatDecimalString()
                imgMovie.load(movie.imageUrl) {
                    crossfade(true)
                    error(R.drawable.no_photo)
                }
                imgFav.setColorFilter(if (movie.isFavourite) Color.RED else Color.GRAY)
                imgFav.setOnClickListener {
                    movie.isFavourite = !movie.isFavourite
                    onItemSelected(movie)
                    imgFav.setColorFilter(if (movie.isFavourite) Color.RED else Color.GRAY)
                }
                imgMovie.setOnClickListener {
                    onClick(movie)
                }
                movieCard.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }

    object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem && oldItem.isFavourite == newItem.isFavourite
        }

    }
}