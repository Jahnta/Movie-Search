package com.example.moviesearch

data class MoviesSearchResponse(val searchType: String,
                                val expression: String,
                                val results: List<Movie>)