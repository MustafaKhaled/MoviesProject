package com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases

import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.FlickrImagesConsumer
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.core.PrimaryUseCase
import io.reactivex.Single
import javax.inject.Inject

class FetchImagesUseCase @Inject constructor(private val flickrImagesConsumer: FlickrImagesConsumer):
    PrimaryUseCase<FlickrModel>() {

    override fun consumePrimaryUseCase(): Single<FlickrModel> {
        return Single.just(FlickrModel(0,"",1,2,2,"","","",""))
//        return flickrImagesConsumer.fetchFlickrImages()
    }
}