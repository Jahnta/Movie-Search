package com.example.moviesearch

import com.example.moviesearch.data.MoviesRepositoryImpl
import com.example.moviesearch.data.network.RetrofitNetworkClient
import com.example.moviesearch.domain.api.MoviesInteractor
import com.example.moviesearch.domain.api.MoviesRepository
import com.example.moviesearch.domain.impl.MoviesInteractorImpl

object Creator {
    private fun getMoviesRepositiry(): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient())
    }

    private fun provideMoviesInteractor(): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepositiry())
    }
}