package com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.module

import androidx.lifecycle.ViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.ViewModelKey
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.scope.PresentationScope
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MoviesSharedViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MovieDetailsViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [SchedulersModule::class])
interface ViewModelFactoryModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesSharedViewModel::class)
    @PresentationScope
    fun bindAllMoviesViewModel(moviesSharedViewModel: MoviesSharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    @PresentationScope
    fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel


}