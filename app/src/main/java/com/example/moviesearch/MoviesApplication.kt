package com.example.moviesearch

import android.app.Application
import com.example.moviesearch.presentation.movies.MoviesSearchPresenter

class MoviesApplication : Application() {

    var moviesSearchPresenter : MoviesSearchPresenter? = null
}