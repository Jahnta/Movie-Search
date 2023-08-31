package com.example.moviesearch.presentation.movies

import com.example.moviesearch.domain.models.Movie

interface MoviesView {

    fun showPlaceholderMessage(isVisible: Boolean)

    fun showMoviesList(isVisible: Boolean)

    fun showProgressBar(isVisible: Boolean)

    fun changePlaceholderText(newPlaceholderText: String)

    fun updateMovieList(newMovieList: List<Movie>)

    fun showMessage(message: String)

}