package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.FetchImagesUseCase
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(private val fetchImagesUseCase: FetchImagesUseCase): ViewModel() {

    fun requestImagesFromFlickr(title: String){
//        fetchImagesUseCase.execute(
////            onSuccess = {
////                getLatestNewsLiveData.postValue(Results.Success(it))
////            },
////            onFailure = {
////                val error  = errorHandling.handleApiErrors(it)
////                getLatestNewsLiveData.postValue(Results.Error(error))
////            },
////            loading = {
////                getLatestNewsLiveData.postValue(Results.Loading())
////            }
//        )
    }

    fun fetchImagesFlickr() = liveData<Results<FlickrModel>> {
        emit(Results.Loading())

    }

}