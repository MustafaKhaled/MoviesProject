package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val useCase: AllMoviesUseCase): ViewModel() {

    var readMovies = liveData(Dispatchers.IO){
        emit(Results.Loading())
        val result = useCase.getMovies()
        emit(result)
    }
}