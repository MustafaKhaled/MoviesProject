package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.FetchImagesUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val fetchImagesUseCase: FetchImagesUseCase): ViewModel() {
    val photosMutableLiveData = MutableLiveData<Results>()
    private val compositeDisposable = CompositeDisposable()


    fun requestImagesFromFlickr(title: String) {
        fetchImagesUseCase.execute(
            onSuccess = {
                photosMutableLiveData.postValue(Results.Success(it))
            },
            onFailure = {
                photosMutableLiveData.postValue(Results.Error(it))
            },
            loading = {
                photosMutableLiveData.postValue(Results.Loading)
            },
            params = title
        )
    }

    fun observeFlicksPhotos(): LiveData<Results>{
            return photosMutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}