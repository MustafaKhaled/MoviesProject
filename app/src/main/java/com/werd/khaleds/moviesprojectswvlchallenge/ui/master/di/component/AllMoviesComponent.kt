package com.werd.khaleds.moviesprojectswvlchallenge.ui.master.di.component

import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.PresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.ui.details.MovieDetailsFragment
import com.werd.khaleds.moviesprojectswvlchallenge.ui.master.AllMoviesFragment
import com.werd.khaleds.moviesprojectswvlchallenge.ui.master.di.scope.AllMoviesScope
import dagger.Component
@AllMoviesScope
@Component(dependencies = [PresentationComponent::class])
interface AllMoviesComponent {
    fun inject(allMoviesFragment: AllMoviesFragment)
}