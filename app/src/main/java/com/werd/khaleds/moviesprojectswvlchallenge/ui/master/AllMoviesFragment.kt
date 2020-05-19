package com.werd.khaleds.moviesprojectswvlchallenge.ui.master

import android.os.Bundle
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.fragment_all_movies.*
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
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.module.MoviesLocalResultModule
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.DaggerPresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MoviesSharedViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.ui.master.di.component.DaggerAllMoviesComponent
import com.werd.khaleds.moviesprojectswvlchallenge.util.snack
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllMoviesFragment : Fragment() {
    private val TAG = javaClass.simpleName
    private lateinit var sharedViewModel: MoviesSharedViewModel
    private lateinit var adapter: AllMoviesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var ascendingOrder = true

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val depend = DaggerAllMoviesComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder()
                    .dataComponent(
                        DaggerDataComponent.builder().moviesLocalResultModule(
                            MoviesLocalResultModule()
                        )
                            .applicationComponent(MyApplication.applicationComponent).build()
                    )
                    .build()
            ).build()
        depend.inject(this)

        sharedViewModel = activity?.let { ViewModelProvider(it, viewModelFactory).get(MoviesSharedViewModel::class.java) }!!
        startMoviesParsing()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        sharedViewModel.readMovies().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it) {
                    is Loading -> {
                        showResults(false)
                    }

                    is Success -> {
                        showResults(true)
                        Log.d(TAG, "Data observed ".plus(it.data))
                        addMoviesList(it.data as MoviesLocalResult)

                    }

                    is Error -> {
                        moviesList.snack(getString(R.string.error_fetch_movies))
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu);
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val menuItem = menu.findItem(R.id.order)
        if (ascendingOrder) {
            menuItem.title = getString(R.string.descending_menu)
            ascendingOrder = false
        } else {
            menuItem.title = getString(R.string.ascending_menu)
            ascendingOrder = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.order) {
            reverseList()
        }
        else if(item.itemId == R.id.search){
            Navigation.findNavController(moviesList).navigate(R.id.action_allMoviesFragment_to_searchFragment)
        }
        return true
    }

    private fun startMoviesParsing() {
        lifecycleScope.launch {
            whenCreated {
                sharedViewModel.parseJson()
            }
        }
    }

    private fun showResults(show: Boolean) {
        if (show) {
            moviesList.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        } else {
            moviesList.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView() {
        adapter = AllMoviesAdapter { movie: MovieItem -> movieItemClicked(movie) }
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        moviesList.layoutManager = layoutManager
        moviesList.adapter = adapter
    }

    private fun movieItemClicked(movieItem: MovieItem) {
        val action = AllMoviesFragmentDirections.actionAllMoviesFragmentToMovieDetails(movieItem)
        Navigation.findNavController(moviesList).navigate(action)
    }

    private fun addMoviesList(moviesList: MoviesLocalResult) {
        Log.d(TAG, "Add movies to RecyclerView ".plus(moviesList.movies))
        adapter.addAll(moviesList.movies)
    }

    private fun reverseList() {
        if(layoutManager.reverseLayout && layoutManager.stackFromEnd){
            layoutManager.reverseLayout = false
            layoutManager.stackFromEnd = false
        }
        else{
            layoutManager.reverseLayout = true
            layoutManager.stackFromEnd = true
        }
    }



}


