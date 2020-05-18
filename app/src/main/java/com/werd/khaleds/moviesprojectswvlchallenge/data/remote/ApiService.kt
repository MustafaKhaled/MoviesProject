package com.werd.khaleds.moviesprojectswvlchallenge.data.remote

import com.werd.khaleds.moviesprojectswvlchallenge.BuildConfig
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("&format=json&nojsoncallback=​1​&page=​1​&per_pa ge=​10")
    fun fetchMovieImages(@Query("api_key") apiKey: String, @Query("text") title: String)
}