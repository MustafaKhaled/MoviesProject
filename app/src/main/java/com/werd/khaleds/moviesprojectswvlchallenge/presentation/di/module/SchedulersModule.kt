package com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class SchedulersModule {
    @Provides
    @Named(value = IO_SCHEDULER)
    fun bindIoScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named(value = MAIN_THREAD_SCHEDULER)
    fun bindMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {
        const val IO_SCHEDULER = "IO_SCHEDULER"
        const val MAIN_THREAD_SCHEDULER = "MAIN_THREAD_SCHEDULER"
    }
}
