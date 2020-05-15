package com.werd.khaleds.moviesprojectswvlchallenge.domain.repo

import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult

interface MoviesParsable {
    fun parseMovies()
    fun getParsedMovies(): MoviesLocalResult
    fun saveParsedData()
}