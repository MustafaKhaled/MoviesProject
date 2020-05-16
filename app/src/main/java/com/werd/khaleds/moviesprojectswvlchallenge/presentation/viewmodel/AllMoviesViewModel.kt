package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllMoviesViewModel @Inject constructor(private val useCase: AllMoviesUseCase):ViewModel() {
    private val TAG = javaClass.simpleName
    fun parseJson(){
        viewModelScope.launch(Dispatchers.Default){
            Log.d(TAG,"current thread is ".plus(Thread.currentThread().name))
            useCase.parseMovies()
        }
    }

    var readMovies: LiveData<Results<MoviesLocalResult>>  = liveData(Dispatchers.IO){
        val result = useCase.getMovies()
        emit(result)
    }
}