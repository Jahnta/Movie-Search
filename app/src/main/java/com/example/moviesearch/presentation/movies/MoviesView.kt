package com.example.moviesearch.presentation.movies

import com.example.moviesearch.domain.models.MoviesState

interface MoviesView {

    fun render(state: MoviesState)

    fun showMessage(message: String)

}