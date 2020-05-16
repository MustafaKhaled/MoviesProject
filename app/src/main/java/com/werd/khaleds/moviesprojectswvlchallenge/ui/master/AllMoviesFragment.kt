package com.werd.khaleds.moviesprojectswvlchallenge.ui.master

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results.*
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.component.DaggerDataComponent
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.DaggerPresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.AllMoviesViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.ui.master.di.component.DaggerAllMoviesComponent
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllMoviesFragment : Fragment() {
    private val TAG = javaClass.simpleName
    private lateinit var viewModel: AllMoviesViewModel
    private lateinit var adapter: AllMoviesAdapter
    private val ascendingMovies = HashMap<Long, MovieItem>()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAllMoviesComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder()
                    .dataComponent(
                        DaggerDataComponent.builder()
                            .applicationComponent(MyApplication.applicationComponent).build()
                    )
                    .build()
            ).build().inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(AllMoviesViewModel::class.java)
        startMoviesParsing()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_movies, container, false)
        setupRecyclerView()
        viewModel.readMovies.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it) {
                    is Loading -> {
                        showResults(false)
                    }

                    is Success -> {
                        showResults(true)

                    }

                    is Error -> {

                    }
                }
            }
        })
        return view
    }

    private fun startMoviesParsing() {
        lifecycleScope.launch {
            whenCreated {
                viewModel.parseJson()
            }
        }
    }

    fun showResults(show: Boolean) {
        if (show) {
            moviesList.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        } else {
            moviesList.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView() {
        adapter = AllMoviesAdapter { article: MovieItem -> movieItemClicked(article) }
        moviesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        moviesList.adapter = adapter
    }

    private fun movieItemClicked(movieItem: MovieItem) {
        val action = AllMoviesFragmentDirections.actionAllMoviesFragmentToMovieDetails(movieItem)
        Navigation.findNavController(moviesList).navigate(action)
    }


}


