package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.FetchImagesUseCase
import io.reactivex.internal.operators.single.SingleJust
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MovieDetailsViewModelTest{

    @Mock
    lateinit var fetchImagesUseCase: FetchImagesUseCase

    lateinit var viewModel: MovieDetailsViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this);
        viewModel = MovieDetailsViewModel(fetchImagesUseCase)
    }

    @Test
    fun `check `(){
        whenever(viewModel.requestImagesFromFlickr("")).thenReturn(fetchImagesUseCase.execute(any(),any(),
            any(),any()))
//        viewModel.requestImagesFromFlickr("title")

    }


}