package com.werd.khaleds.moviesprojectswvlchallenge.presentation.di

import androidx.lifecycle.ViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.scope.PresentationScope
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
@PresentationScope
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)