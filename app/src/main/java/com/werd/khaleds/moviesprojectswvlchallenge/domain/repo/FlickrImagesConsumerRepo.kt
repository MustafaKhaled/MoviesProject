package com.werd.khaleds.moviesprojectswvlchallenge.domain.repo

import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.endpoints.ApiService
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrPhotosResponse
import io.reactivex.Observable
import io.reactivex.Single

interface FlickrImagesConsumerRepo  {
    fun fetchFlickrImages (title: String?): Single<FlickrPhotosResponse>
}