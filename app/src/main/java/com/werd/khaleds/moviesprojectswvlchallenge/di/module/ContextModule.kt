package com.werd.khaleds.moviesprojectswvlchallenge.di.module

import android.content.Context
import com.werd.khaleds.moviesprojectswvlchallenge.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule constructor(private val context: Context) {
    @ApplicationScope
    @Provides
    fun provideContext(): Context {
        return context
    }

}