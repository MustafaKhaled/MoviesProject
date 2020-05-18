package com.werd.khaleds.moviesprojectswvlchallenge.domain.repo

import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.endpoints.ApiService
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import io.reactivex.Single

interface FlickrImagesConsumer  {
    fun fetchFlickrImages (title: String): Single<FlickrModel>
}