package com.werd.khaleds.moviesprojectswvlchallenge.ui.details.di.component

import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.PresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.ui.details.MovieDetailsFragment
import com.werd.khaleds.moviesprojectswvlchallenge.ui.details.di.scope.MovieDetailsScope
import dagger.Component

@MovieDetailsScope
@Component(dependencies = [PresentationComponent::class])
interface MovieDetailsComponent {
    fun inject(detailsFragment: MovieDetailsFragment)

}