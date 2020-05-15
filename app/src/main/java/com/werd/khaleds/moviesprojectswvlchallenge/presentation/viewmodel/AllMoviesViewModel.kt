package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication.Companion.appContext
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

class AllMoviesViewModel @Inject constructor():ViewModel() {
    private val TAG = javaClass.simpleName
    fun parseJson(){
        viewModelScope.launch(Dispatchers.Default){
            Log.d(TAG,"current thread is ".plus(Thread.currentThread().name))
            val gson = Gson()
            val fileName = "movies.json"
            val jsonAsString = appContext.assets.open(fileName).bufferedReader().use{
                it.readText()
            }
            val listType: Type = object : TypeToken<MoviesLocalResult>() {}.type
            val result  = gson.fromJson<MoviesLocalResult>(jsonAsString,listType)
            Log.d(TAG,"result size ".plus(result.movies?.size))
        }

    }
}