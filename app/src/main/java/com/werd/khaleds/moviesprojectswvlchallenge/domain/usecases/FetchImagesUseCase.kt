package com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases

import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.FlickrImagesConsumer
import io.reactivex.Single
import javax.inject.Inject

class FetchImagesUseCase @Inject constructor(private val flickrImagesConsumer: FlickrImagesConsumer) {
    fun fetchFlickrImages(title: String): Single<FlickrModel> {
        return flickrImagesConsumer.fetchFlickrImages(title)
    }
}