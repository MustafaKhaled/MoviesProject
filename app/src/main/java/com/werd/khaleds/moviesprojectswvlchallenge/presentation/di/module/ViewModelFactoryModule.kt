package com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.module

import androidx.lifecycle.ViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.ViewModelKey
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.scope.PresentationScope
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.AllMoviesViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MovieDetailsViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.SearchViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelFactoryModule {
    @Binds
    @IntoMap
    @ViewModelKey(AllMoviesViewModel::class)
    @PresentationScope
    fun bindAllMoviesViewModel(allMoviesViewModel: AllMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    @PresentationScope
    fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    @PresentationScope
    fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

}