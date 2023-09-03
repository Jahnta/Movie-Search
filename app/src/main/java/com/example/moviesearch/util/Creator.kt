package com.example.moviesearch.util

import android.app.Activity
import android.content.Context
import com.example.moviesearch.data.MoviesRepositoryImpl
import com.example.moviesearch.data.network.RetrofitNetworkClient
import com.example.moviesearch.domain.api.MoviesInteractor
import com.example.moviesearch.domain.api.MoviesRepository
import com.example.moviesearch.domain.impl.MoviesInteractorImpl
import com.example.moviesearch.presentation.movies.MoviesSearchPresenter
import com.example.moviesearch.presentation.poster.PosterPresenter
import com.example.moviesearch.presentation.movies.MoviesView
import com.example.moviesearch.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(
        context: Context
    ) : MoviesSearchPresenter {
        return MoviesSearchPresenter(
            context =context
        )
    }

    fun providePosterPresenter(
        posterView: PosterView,
        imageUrl: String
    ): PosterPresenter {
        return PosterPresenter(
            view = posterView,
            imageUrl = imageUrl
        )
    }
}