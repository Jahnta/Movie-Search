package com.example.moviesearch.domain.impl

import com.example.moviesearch.domain.api.MoviesInteractor
import com.example.moviesearch.domain.api.MoviesRepository
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MovieConsumer) {
        executor.execute {
            consumer.consume(repository.searchMovies(expression))
        }
    }
}