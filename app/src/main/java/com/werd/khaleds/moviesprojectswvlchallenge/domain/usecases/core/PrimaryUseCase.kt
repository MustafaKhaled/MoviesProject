package com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.core

import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.SchedulerSupport.IO
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer

abstract class PrimaryUseCase<in Params, T> internal constructor() : UseCaseRxJavaDisposable() {

    internal abstract fun buildObservable(params: Params?): Single<T>

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onFailure: ((t: Throwable) -> Unit),
        loading: ((it: Disposable) -> Unit),
        params: Params?
    ) {
        dispose()
        disposable = buildObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(loading)
            .subscribe(onSuccess, onFailure)

        disposable?.let {
            compositeDisposable.add(it)
        }
    }
}
