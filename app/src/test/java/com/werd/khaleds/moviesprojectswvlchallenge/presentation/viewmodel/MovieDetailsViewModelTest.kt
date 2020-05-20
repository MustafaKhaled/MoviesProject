package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrPhotosResponse
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.FetchImagesUseCase
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.exceptions.base.MockitoException
import org.mockito.stubbing.Answer
import java.lang.RuntimeException
import java.util.concurrent.Callable

@RunWith(JUnit4::class)
class MovieDetailsViewModelTest {
    @get:Rule
    val instanceExector = InstantTaskExecutorRule()

    @Mock
    lateinit var fetchImagesUseCase: FetchImagesUseCase

    @Mock
    lateinit var viewState: Observer<Results>

    @Mock
    lateinit var flickrPhotosResponse: FlickrPhotosResponse

    @Mock
    lateinit var networkException: NetworkErrorException

    private lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        viewModel = MovieDetailsViewModel(fetchImagesUseCase)
            .apply { observeFlicksPhotos().observeForever(viewState) }

    }

    @Test
    fun `check if data successfully retrieved`() {
        val data = Results.Success(flickrPhotosResponse)
        whenever(fetchImagesUseCase.requestPhotos(ArgumentMatchers.anyString())).thenReturn(
            Single.just(
                flickrPhotosResponse
            )
        )
        viewModel.requestImagesFromFlickr(ArgumentMatchers.anyString())
        verify(viewState).onChanged(Results.Loading)
        Assert.assertEquals(LiveDataTestUtil.getValue(viewModel.observeFlicksPhotos()),data)
    }

    @Test
    fun `check error`(){
        val data = Results.Success(flickrPhotosResponse)
        whenever(fetchImagesUseCase.requestPhotos(ArgumentMatchers.anyString())).thenReturn(Single.error(networkException))
        viewModel.requestImagesFromFlickr(ArgumentMatchers.anyString())
        verify(viewState).onChanged(Results.Loading)
        Assert.assertNotEquals(LiveDataTestUtil.getValue(viewModel.observeFlicksPhotos()),data)
    }
}
