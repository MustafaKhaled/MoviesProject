package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllMoviesViewModel @Inject constructor(private val useCase: AllMoviesUseCase):ViewModel() {
    private val moviesMutableLiveData = MutableLiveData<Results<MoviesLocalResult>>()
    init {
        moviesMutableLiveData.postValue(Results.Loading())
    }
    fun parseJson(){
        viewModelScope.launch(Dispatchers.Default){
            useCase.parseMovies()
            moviesMutableLiveData.postValue(useCase.getMovies())
        }
    }

    fun readMovies(): LiveData<Results<MoviesLocalResult>> {
        return  moviesMutableLiveData
    }
}