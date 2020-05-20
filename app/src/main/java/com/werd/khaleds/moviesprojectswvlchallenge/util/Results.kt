package com.werd.khaleds.moviesprojectswvlchallenge.util

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

sealed class Results {
    data class Success(val data: Any) : Results()
    object Loading : Results()
    class Error(val throwable: Throwable) : Results()
}