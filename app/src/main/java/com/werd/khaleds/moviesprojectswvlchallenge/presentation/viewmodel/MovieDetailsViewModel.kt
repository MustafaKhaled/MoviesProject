package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val useCase: AllMoviesUseCase): ViewModel() {

    var readMovies = liveData(Dispatchers.IO){
        emit(Results.Loading())
        val result = useCase.getMovies()
        emit(result)
    }
}