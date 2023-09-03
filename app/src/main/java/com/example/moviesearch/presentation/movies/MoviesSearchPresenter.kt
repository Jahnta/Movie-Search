package com.example.moviesearch.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.moviesearch.R
import com.example.moviesearch.domain.api.MoviesInteractor
import com.example.moviesearch.domain.models.Movie
import com.example.moviesearch.domain.models.MoviesState
import com.example.moviesearch.ui.movies.MoviesAdapter
import com.example.moviesearch.util.Creator

class MoviesSearchPresenter(
    private val context: Context,
) {

    private var view: MoviesView? = null
    private var state: MoviesState? = null
    private var latestSearchText: String? = null

    fun attachView(view: MoviesView) {
        this.view = view
        state?.let { view.render(it) }
    }

    fun detachView() {
        this.view = null
    }

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val handler = Handler(Looper.getMainLooper())

    private val searchRunnable = Runnable {
        val newSearchText = latestSearchText ?: ""
        searchRequest(newSearchText)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }
        this.latestSearchText = changedText
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(MoviesState.Loading)

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MovieConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        if (foundMovies != null) {
                            movies.clear()
                            movies.addAll(foundMovies)
                        }
                        when {
                            errorMessage != null -> {
                                renderState(MoviesState.Error(context.getString(R.string.something_went_wrong)))
                            }

                            movies.isEmpty() -> {
                                renderState(
                                    MoviesState.Empty(context.getString(R.string.nothing_found))
                                )
                            }

                            else -> {
                                renderState(MoviesState.Content(movies))
                            }
                        }
                    }
                }
            })
        }
    }

    private fun renderState(state: MoviesState) {
        this.state = state
        this.view?.render(state)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private val movies = ArrayList<Movie>()

    fun onDestroy() {
        handler.removeCallbacks(searchRunnable)
    }

}