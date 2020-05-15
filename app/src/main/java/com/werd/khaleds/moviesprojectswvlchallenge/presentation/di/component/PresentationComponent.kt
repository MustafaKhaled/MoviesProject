package com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component
import com.werd.khaleds.moviesprojectswvlchallenge.di.component.ApplicationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.scope.PresentationScope
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.module.ViewModelFactoryModule
import dagger.Component

@PresentationScope
@Component(modules = [ViewModelFactoryModule::class],dependencies = [ApplicationComponent::class])
interface PresentationComponent {

    fun exposeViewModel(): ViewModelFactory

}