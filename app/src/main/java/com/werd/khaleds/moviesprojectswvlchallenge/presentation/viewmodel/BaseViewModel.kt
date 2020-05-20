package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.core.util.Preconditions
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

open class BaseViewModel @Inject constructor (protected val subscribeOn: Scheduler, protected val observeOn: Scheduler) :
    ViewModel() {


}