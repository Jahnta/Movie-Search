package com.example.moviesearch.domain.api

import com.example.moviesearch.domain.models.Movie

interface MoviesInteractor {
    fun searchMovies(expression: String, consumer: MovieConsumer)

    interface MovieConsumer {
        fun consume(foundMovies: List<Movie>?, errorMessage: String?)
    }
}