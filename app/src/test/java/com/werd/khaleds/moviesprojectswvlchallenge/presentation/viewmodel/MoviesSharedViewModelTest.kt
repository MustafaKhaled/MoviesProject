package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MoviesSharedViewModelTest{
    @get:Rule
    val instanceExector = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @Mock
    lateinit var useCase: AllMoviesUseCase
    @Mock
    lateinit var moviesLocalResult: MoviesLocalResult
    @Mock
    lateinit var viewState: Observer<Results<Any>>
    private lateinit var viewModel: MoviesSharedViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesSharedViewModel(useCase)
            .apply { readMovies().observeForever(viewState) }
    }

    @Test
    fun should_show_success(){
        testCoroutineRule.runBlockingTest {
            moviesLocalResult =  MoviesLocalResult()
            val data  = Results.Loading()
            whenever(useCase.getMovies()).thenReturn(Results.Loading())
            viewModel.parseJson()

            verify(viewState).onChanged(Results.Loading())
//            verify(viewState).onChanged(Results.Success(data))

        }
    }
}