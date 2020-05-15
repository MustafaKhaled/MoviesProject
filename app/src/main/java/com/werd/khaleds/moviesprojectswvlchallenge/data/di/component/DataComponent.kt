package com.werd.khaleds.moviesprojectswvlchallenge.data.di.component

import com.werd.khaleds.moviesprojectswvlchallenge.data.di.module.RepoModule
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.scope.DataComponentScope
import com.werd.khaleds.moviesprojectswvlchallenge.di.component.ApplicationComponent
import dagger.Component
@DataComponentScope
@Component(dependencies = [ApplicationComponent::class], modules = [RepoModule::class])
interface DataComponent {

}