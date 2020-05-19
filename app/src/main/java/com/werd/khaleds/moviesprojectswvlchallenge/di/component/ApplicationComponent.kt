package com.werd.khaleds.moviesprojectswvlchallenge.di.component

import android.content.Context
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.endpoints.ApiService
import com.werd.khaleds.moviesprojectswvlchallenge.di.module.ContextModule
import com.werd.khaleds.moviesprojectswvlchallenge.di.module.RetrofitModule
import com.werd.khaleds.moviesprojectswvlchallenge.di.scope.ApplicationScope
import dagger.Component
import retrofit2.Retrofit
@ApplicationScope
@Component(modules = [ContextModule::class, RetrofitModule::class])
interface ApplicationComponent {
    fun exposeRetrofit(): Retrofit
    fun exposeContext(): Context
    fun exposeApiServices(): ApiService
}