package com.werd.khaleds.moviesprojectswvlchallenge.data.di.module

import com.werd.khaleds.moviesprojectswvlchallenge.data.repo.MoviesParsableImpl
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.MoviesParsable
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @Binds
    fun bindsMoviesParsable(moviesParsableImpl: MoviesParsableImpl): MoviesParsable
}