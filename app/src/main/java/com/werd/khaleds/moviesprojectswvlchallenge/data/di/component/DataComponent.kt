package com.werd.khaleds.moviesprojectswvlchallenge.data.di.component

import com.werd.khaleds.moviesprojectswvlchallenge.data.di.module.RepoModule
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.scope.DataComponentScope
import com.werd.khaleds.moviesprojectswvlchallenge.di.component.ApplicationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.MoviesParsable
import dagger.Component
@DataComponentScope
@Component(modules = [RepoModule::class],dependencies = [ApplicationComponent::class])
interface DataComponent {
    fun getMoviesParsable(): MoviesParsable
}