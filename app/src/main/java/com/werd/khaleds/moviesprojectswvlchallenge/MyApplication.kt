package com.werd.khaleds.moviesprojectswvlchallenge

import android.app.Application
import android.content.Context
import com.avoid.covid19.di.component.ApplicationComponent
import com.avoid.covid19.di.component.DaggerApplicationComponent
import com.avoid.covid19.di.module.ContextModule
import com.avoid.covid19.di.module.OkHttpModule
import com.avoid.covid19.di.module.RetrofitModule

class MyApplication: Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
        lateinit  var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        applicationComponent = DaggerApplicationComponent.builder().contextModule(ContextModule(applicationContext))
            .okHttpModule(
                OkHttpModule()
            ).retrofitModule(RetrofitModule()).build()
    }
}