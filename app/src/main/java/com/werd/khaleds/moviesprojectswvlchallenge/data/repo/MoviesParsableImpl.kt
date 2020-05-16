package com.werd.khaleds.moviesprojectswvlchallenge.data.repo

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.data.utils.FileUtils
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.MoviesParsable
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import java.lang.reflect.Type
import javax.inject.Inject

class MoviesParsableImpl @Inject constructor(): MoviesParsable {
    private val TAG = javaClass.simpleName
    private val fileUtils = FileUtils()
    private var moviesLocalResult =  MoviesLocalResult(null)
    override fun parseMovies() {
        Log.d(TAG,"current thread is ".plus(Thread.currentThread().name))
        val gson = Gson()
        val fileName = "movies.json"
        val jsonAsString = MyApplication.appContext.assets.open(fileName).bufferedReader().use{
            it.readText()
        }
        val listType: Type = object : TypeToken<MoviesLocalResult>() {}.type
        moviesLocalResult  = gson.fromJson(jsonAsString,listType)
        Log.d(TAG,"result size ".plus(moviesLocalResult.movies?.size))
    }

    override fun getParsedMovies(): Results<MoviesLocalResult> {
        return Results.Success(moviesLocalResult)
    }

    override fun saveParsedData() {
        fileUtils.writeFile(moviesLocalResult)
    }


}