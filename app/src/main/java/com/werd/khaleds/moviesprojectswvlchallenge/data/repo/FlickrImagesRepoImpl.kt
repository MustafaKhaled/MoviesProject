package com.werd.khaleds.moviesprojectswvlchallenge.data.repo

import com.werd.khaleds.moviesprojectswvlchallenge.BuildConfig
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.endpoints.ApiService
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrPhotosResponse
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.FlickrImagesConsumerRepo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class FlickrImagesRepoImpl @Inject constructor(private val apiService: ApiService): FlickrImagesConsumerRepo {
    override fun fetchFlickrImages(title: String?): Single<FlickrPhotosResponse> {
       return title?.let { apiService.fetchMovieImages(BuildConfig.APIKEY, it) }!!
    }

}