package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrPhotosResponse
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.FetchImagesUseCase
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val fetchImagesUseCase: FetchImagesUseCase): ViewModel() {
    val photosMutableLiveData = MutableLiveData<Results<FlickrPhotosResponse>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        photosMutableLiveData.postValue(Results.Loading())
    }

    fun requestImagesFromFlickr(title: String) {
        fetchImagesUseCase.execute(
            onSuccess = {
                photosMutableLiveData.postValue(Results.Success(it))
            },
            onFailure = {
                photosMutableLiveData.postValue(Results.Error(it.message))
            },
            loading = {
                photosMutableLiveData.postValue(Results.Loading())
            },
            params = title
        )
    }

    fun observeFlicksPhotos(): LiveData<Results<FlickrPhotosResponse>>{
            return photosMutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

//    override fun onComplete() {
//    }
//    override fun onNext(t: FlickrPhotosResponse) {
//        photosMutableLiveData.postValue(Results.Success(t))
//    }
//
//    override fun onError(e: Throwable) {
//        photosMutableLiveData.postValue(Results.Error(e.message))
//    }

}