package com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.core

import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrModel
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

abstract class PrimaryUseCase<in Params, Result> internal constructor() {

    internal abstract fun buildObservable(params: Params?): Observable<Result>

    fun execute(params: Params? = null): Observable<Result> {
        return buildObservable(params)
            .subscribeOn(Schedulers.io())
            // Unfortunately RxJava had a bug that if any Exceptions were being thrown later
            // in the stream they would incorrectly cut ahead of the successful emissions
            // and break the flow.
            // In order to fix this, an overload was added in version 1.1.1
            // for observeOn(Scheduler scheduler, boolean delayError)
            // in order to signal the Scheduler to respect the delaying of errors.
            // https://medium.com/yammer-engineering/chaining-multiple-sources-with-rxjava-20eb6850e5d9
            .observeOn(AndroidSchedulers.mainThread())
    }
}
