package com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases

import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.MoviesParsable
import javax.inject.Inject

class AllMoviesUseCase @Inject constructor(private val moviesParsable: MoviesParsable){
    fun parseMovies(){
        moviesParsable.parseMovies()
    }
}