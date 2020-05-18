package com.werd.khaleds.moviesprojectswvlchallenge.data.di.module

import com.werd.khaleds.moviesprojectswvlchallenge.data.di.scope.DataComponentScope
import com.werd.khaleds.moviesprojectswvlchallenge.data.repo.MoviesParsableImpl
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.MoviesParsable
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @DataComponentScope
    @Binds
    fun bindsMoviesParsable(moviesParsableImpl: MoviesParsableImpl): MoviesParsable
    
}