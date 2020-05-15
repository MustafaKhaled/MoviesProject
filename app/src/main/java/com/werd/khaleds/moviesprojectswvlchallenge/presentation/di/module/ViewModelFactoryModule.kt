package com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.module

import androidx.lifecycle.ViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.ViewModelKey
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.AllMoviesViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelFactoryModule {
    @Binds
    @IntoMap
    @ViewModelKey(AllMoviesViewModel::class)
    fun bindAllMoviesViewModel(allMoviesViewModel: AllMoviesViewModel): ViewModel

}