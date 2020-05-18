package com.werd.khaleds.moviesprojectswvlchallenge.data.repo

import com.werd.khaleds.moviesprojectswvlchallenge.BuildConfig
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.endpoints.ApiService
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.FlickrImagesConsumer
import io.reactivex.Single
import javax.inject.Inject

class FlickrImagesRepoImpl @Inject constructor(private val apiService: ApiService): FlickrImagesConsumer {
    override fun fetchFlickrImages(title: String): Single<FlickrModel> {
        return apiService.fetchMovieImages(BuildConfig.APIKEY, title)
    }
}