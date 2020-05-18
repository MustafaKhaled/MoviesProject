package com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class UseCaseRxJavaDisposable {
    protected var disposable: Disposable? = null
    protected val compositeDisposable = CompositeDisposable()

    fun dispose(){
        disposable?.let {
            if(!it.isDisposed){
                it.dispose()
            }
        }
    }

    fun disposeAll(){
        compositeDisposable.dispose()
    }
}