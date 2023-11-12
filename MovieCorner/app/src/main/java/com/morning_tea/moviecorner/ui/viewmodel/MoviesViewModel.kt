package com.morning_tea.moviecorner.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.morning_tea.moviecorner.domain.model.Movie
import com.morning_tea.moviecorner.domain.use_case.GetPopularMoviesUseCase
import com.morning_tea.moviecorner.domain.use_case.GetUpcomingMoviesUseCase
import com.morning_tea.moviecorner.domain.use_case.SaveFavouriteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val saveFavouriteMovieUseCase: SaveFavouriteMovieUseCase
) : ViewModel() {

    private val _popularMovies = MutableLiveData<PagingData<Movie>>()
    val popularMovies : LiveData<PagingData<Movie>> get() = _popularMovies

    private val _upcomingMovies = MutableLiveData<PagingData<Movie>>()
    val upcomingMovies : LiveData<PagingData<Movie>> get() = _upcomingMovies

    fun getPopularMovies() = viewModelScope.launch {
        getPopularMoviesUseCase.invoke().cachedIn(viewModelScope).collect {
            _popularMovies.postValue(it)
        }
    }

    fun getUpcomingMovies() = viewModelScope.launch {
        getUpcomingMoviesUseCase.invoke().cachedIn(viewModelScope).collect {
            _upcomingMovies.postValue(it)
        }
    }

    fun onItemSelected(movie: Movie) = viewModelScope.launch {
        saveFavouriteMovieUseCase.invoke(movie)
    }
}