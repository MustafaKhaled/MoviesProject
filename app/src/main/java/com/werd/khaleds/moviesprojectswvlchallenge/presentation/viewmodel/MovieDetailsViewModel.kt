package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.FetchImagesUseCase
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(val fetchImagesUseCase: FetchImagesUseCase): ViewModel() {

    fun requestImagesFromFlickr(title: String){
        return fetchImagesUseCase.execute(title)
            .subscribe(object : DisposableObserver<FlickrModel>() {
                override fun onComplete() {

                }

                override fun onNext(t: FlickrModel) {

                }

                override fun onError(e: Throwable) {

                }

            })
    }

}