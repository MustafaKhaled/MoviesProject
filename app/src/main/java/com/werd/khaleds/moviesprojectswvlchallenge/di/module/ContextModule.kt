package com.avoid.covid19.di.module

import android.content.Context
import com.avoid.covid19.di.scope.ApplicationScope
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