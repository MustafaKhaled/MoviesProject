package com.werd.khaleds.moviesprojectswvlchallenge.data.di.module

import com.werd.khaleds.moviesprojectswvlchallenge.data.di.scope.DataComponentScope
import com.werd.khaleds.moviesprojectswvlchallenge.data.repo.FlickrImagesRepoImpl
import com.werd.khaleds.moviesprojectswvlchallenge.data.repo.MoviesParsableImpl
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.FlickrImagesConsumerRepo
import com.werd.khaleds.moviesprojectswvlchallenge.domain.repo.MoviesParsable
import dagger.Binds
import dagger.Module

@Module
interface RepoModule {
    @DataComponentScope
    @Binds
    fun bindsMoviesParsable(moviesParsableImpl: MoviesParsableImpl): MoviesParsable

    @DataComponentScope
    @Binds
    fun bindsFlickrService(flickrImagesRepoImpl: FlickrImagesRepoImpl): FlickrImagesConsumerRepo
    
}