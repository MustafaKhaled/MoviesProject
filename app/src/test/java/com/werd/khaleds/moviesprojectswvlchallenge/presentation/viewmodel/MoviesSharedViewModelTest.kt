package com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.domain.usecases.AllMoviesUseCase
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import org.hamcrest.core.Is
import org.junit.*
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
    lateinit var viewState: Observer<Results>
    private lateinit var viewModel: MoviesSharedViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MoviesSharedViewModel(useCase)
            .apply { readMovies().observeForever(viewState) }
    }

    @Test
    fun `should show success when data retrieved `(){
        testCoroutineRule.runBlockingTest {
            moviesLocalResult =  MoviesLocalResult()
            val data  = Results.Success(Any())
            whenever(useCase.getMovies()).thenReturn(data)
            viewModel.parseJson()

            verify(viewState).onChanged(Results.Loading)
            verify(viewState).onChanged(data)

        }
    }

    @Test
    fun `should not show results `(){
        testCoroutineRule.runBlockingTest() {
            moviesLocalResult =  MoviesLocalResult()
            val error = Error()
            whenever(useCase.getMovies()).thenThrow(error)
            viewModel.parseJson()

            verify(viewState).onChanged(Results.Loading)
        }
    }


    @Test
    fun `no Interaction should happen`(){
        verifyZeroInteractions(useCase)
    }


}