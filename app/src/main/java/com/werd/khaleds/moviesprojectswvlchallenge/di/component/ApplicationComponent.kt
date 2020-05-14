package com.avoid.covid19.di.component

import android.content.Context
import com.avoid.covid19.di.module.ContextModule
import com.avoid.covid19.di.module.RetrofitModule
import com.avoid.covid19.di.scope.ApplicationScope
import dagger.Component
import retrofit2.Retrofit
@ApplicationScope
@Component(modules = [ContextModule::class,RetrofitModule::class])
interface ApplicationComponent {
    fun exposeRetrofit(): Retrofit
    fun exposeContext(): Context
//    fun exposeApiServices(): ApiServices
}