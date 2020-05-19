package com.werd.khaleds.moviesprojectswvlchallenge.util

import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

sealed class Results<out T: Any>(data: T? = null, message: String? = null) {
    class Success<out T : Any>(val data: T) : Results<T>()
    class Loading : Results<Nothing>()
    class Error(val message: String?) : Results<Nothing>()
}