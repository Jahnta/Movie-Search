package com.example.moviesearch.domain.api

import com.example.moviesearch.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}