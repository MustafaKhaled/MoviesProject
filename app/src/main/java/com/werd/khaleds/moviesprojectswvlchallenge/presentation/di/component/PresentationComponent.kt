package com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component
import com.avoid.covid19.presentation.di.scope.PresentationScope
import com.avoid.covid19.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.module.ViewModelFactoryModule
import dagger.Component

@PresentationScope
@Component(modules = [ViewModelFactoryModule::class])
interface PresentationComponent {

    fun exposeViewModel(): ViewModelFactory

}