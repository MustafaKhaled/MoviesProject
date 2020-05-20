package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.FetchImagesUseCase
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.module.SchedulersModule
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class MovieDetailsViewModel @Inject constructor(
    private val fetchImagesUseCase: FetchImagesUseCase
) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val photosMutableLiveData = MutableLiveData<Results>()

    fun requestImagesFromFlickr(title: String) {

        compositeDisposable.add(
            fetchImagesUseCase.requestPhotos(title)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { photosMutableLiveData.postValue(Results.Loading) }
            .subscribe({ result -> photosMutableLiveData.postValue(Results.Success(result)) },
                { throwable -> photosMutableLiveData.postValue(Results.Error(throwable)) }
            ))
    }

    fun observeFlicksPhotos(): LiveData<Results> {
        return photosMutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    
}


