package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication.Companion.appContext
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import kotlinx.coroutines.launch
import java.lang.reflect.Type

class AllMoviesViewModel:ViewModel() {
    private val TAG = javaClass.simpleName
    fun parseJson(){
        viewModelScope.launch{
            val gson = Gson()
            val fileName = "movies.json"
            val jsonAsString = appContext.assets.open(fileName).bufferedReader().use{
                it.readText()
            }
            val listType: Type = object : TypeToken<List<MovieItem?>?>() {}.type
            val result  = gson.fromJson<List<MovieItem>>(jsonAsString,listType)
            Log.d(TAG,"result size ".plus(result.size))
        }

    }
}