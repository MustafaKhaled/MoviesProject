package com.werd.khaleds.moviesprojectswvlchallenge.ui.search.di.component

import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.PresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.ui.search.SearchFragment
import com.werd.khaleds.moviesprojectswvlchallenge.ui.search.di.scope.SearchScope
import dagger.Component

@SearchScope
@Component(dependencies = [PresentationComponent::class])
interface SearchComponent {
    fun inject(searchFragment: SearchFragment)
}