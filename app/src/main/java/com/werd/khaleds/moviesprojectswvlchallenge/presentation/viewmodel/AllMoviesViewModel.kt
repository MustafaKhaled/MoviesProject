package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication.Companion.appContext
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

class AllMoviesViewModel @Inject constructor(private val useCase: AllMoviesUseCase):ViewModel() {
    private val TAG = javaClass.simpleName
    fun parseJson(){
        viewModelScope.launch(Dispatchers.Default){
            Log.d(TAG,"current thread is ".plus(Thread.currentThread().name))
            useCase.parseMovies()
        }
    }

//    fun readMovies(): MoviesLocalResult = liveData{
//
//    }
}