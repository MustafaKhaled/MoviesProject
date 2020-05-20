package com.werd.khaleds.moviesprojectswvlchallenge.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.component.DaggerDataComponent
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.module.MoviesLocalResultModule
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.FlickrPhotosResponse
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.Photo
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.Photos
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.DaggerPresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MovieDetailsViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.ui.details.adapter.FlickrPhotsAdapter
import com.werd.khaleds.moviesprojectswvlchallenge.ui.master.AllMoviesAdapter
import com.werd.khaleds.moviesprojectswvlchallenge.ui.master.di.component.DaggerAllMoviesComponent
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.util.snack
import kotlinx.android.synthetic.main.all_movies_list_item.*
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.android.synthetic.main.fragment_movie_details.*
import javax.inject.Inject

class MovieDetailsFragment: Fragment() {
    val TAG = javaClass.simpleName
    lateinit var movie: MovieItem
    lateinit var adapter:FlickrPhotsAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MovieDetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAllMoviesComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder()
                    .dataComponent(
                        DaggerDataComponent.builder().moviesLocalResultModule(
                            MoviesLocalResultModule()
                        )
                            .applicationComponent(MyApplication.applicationComponent).build())
                    .build()
            ).build().inject(this)
        setHasOptionsMenu(true)
        bindArguments(arguments)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = movie.title
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
        movie.title?.let { viewModel.requestImagesFromFlickr(it) }
    }

    private fun populateViews() {
            yearDetails.text = movie.year.toString()
            castValue.text = movie.cast?.joinToString(", ")
            genreLabel.text = movie.genres?.joinToString(", ")
            ratingBarDetails.rating = movie.rating?.toFloat()!!
    }

    private fun bindArguments(arguments: Bundle?) {
        if(arguments!=null){
             movie = MovieDetailsFragmentArgs.fromBundle(arguments).movie
        }
    }

    private fun showResults(show: Boolean) {
        if (show) {
            photosRv.visibility = View.VISIBLE
            imagesProgressBar.visibility = View.GONE
        } else {
            photosRv.visibility = View.GONE
            imagesProgressBar.visibility = View.VISIBLE
        }
    }

    private fun observeFlickrPhotos(){
        viewModel.observeFlicksPhotos().observe(viewLifecycleOwner, Observer {

            when(it){
                is  Results.Success -> {
                    showResults(true)
                    val res = it.data as FlickrPhotosResponse
                    adapter.addAll(res.photos?.photo)
                }
                is  Results.Error -> {
                    showResults(true)
                    photosRv.snack(getString(R.string.error_fetch_movies))
                }
                is  Results.Loading -> {
                    showResults(false)
                }
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = FlickrPhotsAdapter()
        val layoutManager = GridLayoutManager(context,2, LinearLayoutManager.VERTICAL, false)
        photosRv.layoutManager = layoutManager
        photosRv.adapter = adapter
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = movie.title
        return inflater.inflate(R.layout.fragment_movie_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateViews()
        observeFlickrPhotos()
        setupRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            activity?.view?.let { Navigation.findNavController(it).navigateUp() }
        return super.onOptionsItemSelected(item)
    }
}