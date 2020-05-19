package com.werd.khaleds.moviesprojectswvlchallenge.data.remote.endpoints

import com.werd.khaleds.moviesprojectswvlchallenge.BuildConfig
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrPhotosResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&page=1&per_page=10/")
    fun fetchMovieImages(@Query("api_key") apiKey: String, @Query("text") title: String): Single<FlickrPhotosResponse>
}