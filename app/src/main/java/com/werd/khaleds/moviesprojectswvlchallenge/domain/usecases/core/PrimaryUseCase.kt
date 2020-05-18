package com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.core

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class PrimaryUseCase<T> : UseCaseRxJavaDisposable() {
    internal abstract fun consumePrimaryUseCase(): Single<T>

    fun execute(
        onSuccess: ((t: T) -> Unit),
        onFailure: ((t: Throwable) -> Unit),
        loading: ((it: Disposable) -> Unit),
        useCase: Observable<T>
    ) {
        dispose()
        disposable = useCase
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(loading)
            .subscribe(onSuccess, onFailure)

        disposable?.let {
            compositeDisposable.add(it)
        }
    }


}