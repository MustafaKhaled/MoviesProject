package com.werd.khaleds.moviesprojectswvlchallenge.data.di.module

import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import dagger.Module
import dagger.Provides

@Module
class MoviesLocalResultModule {
    @Provides
    fun provideMovieLocalResults(): MoviesLocalResult{
        return MoviesLocalResult()
    }
}