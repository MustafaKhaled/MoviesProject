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
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results.*
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.component.DaggerDataComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.DaggerPresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.AllMoviesViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.ui.master.di.component.DaggerAllMoviesComponent
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllMoviesFragment : Fragment() {
    private val TAG = javaClass.simpleName
    lateinit var viewModel: AllMoviesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAllMoviesComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder()
                    .dataComponent(DaggerDataComponent.builder()
                        .applicationComponent(MyApplication.applicationComponent).build())
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
        val view = inflater.inflate(R.layout.fragment_all_movies,container,false)
        viewModel.readMovies.observe(viewLifecycleOwner, Observer {
            if (it!=null){
              when(it){
                  is Loading -> {
                      progressBar.visibility = View.VISIBLE
                  }

                  is Success -> {
                      progressBar.visibility = View.GONE
                  }

                  is Error -> {

                  }
              }
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        button.setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_allMoviesFragment_to_movieDetails)
//        }
    }

    private fun startMoviesParsing() {
        lifecycleScope.launch {
            Log.d(TAG,"current thread is ".plus(Thread.currentThread().name))
            whenCreated {
                viewModel.parseJson()
            }
        }
    }
}