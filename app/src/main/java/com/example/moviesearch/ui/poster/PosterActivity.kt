package com.example.moviesearch.ui.poster

import android.app.Activity
import android.os.Bundle
import com.example.moviesearch.util.Creator
import com.example.moviesearch.R

class PosterActivity : Activity() {

    private val posterController = Creator.providePosterController(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poster)
        posterController.onCreate()
    }
}