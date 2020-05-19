package com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases

import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.FlickrImagesConsumerRepo
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.core.PrimaryUseCase
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class FetchImagesUseCase @Inject constructor(private val flickrImagesConsumerRepo: FlickrImagesConsumerRepo):
    PrimaryUseCase<String, FlickrModel>() {
    override fun buildObservable(params: String?): Observable<FlickrModel> {
        return flickrImagesConsumerRepo.fetchFlickrImages(params).toObservable()
    }


}