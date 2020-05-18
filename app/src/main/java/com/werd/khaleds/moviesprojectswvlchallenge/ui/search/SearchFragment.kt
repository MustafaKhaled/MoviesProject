package com.werd.khaleds.moviesprojectswvlchallenge.ui.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.component.DaggerDataComponent
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.DaggerPresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MovieDetailsViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.SearchViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.ui.MainActivity
import com.werd.khaleds.moviesprojectswvlchallenge.ui.search.di.component.DaggerSearchComponent
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import com.werd.khaleds.moviesprojectswvlchallenge.util.snack
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_all_movies.*
import java.util.*
import javax.inject.Inject


class SearchFragment: Fragment() {
    val TAG = javaClass.simpleName
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: SearchViewModel
    lateinit var sectionedAdapter: SectionedRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        DaggerSearchComponent.builder()
            .presentationComponent(
                DaggerPresentationComponent.builder()
                    .dataComponent(
                        DaggerDataComponent.builder()
                            .applicationComponent(MyApplication.applicationComponent).build())
                    .build()
            ).build().inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sectionedAdapter = SectionedRecyclerViewAdapter()
        return inflater.inflate(R.layout.fragment_search,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.readMovies.observe(this, Observer {
            if (it!=null){
                when (it) {
                    is Results.Success -> {
                        val unsortedMovies = it.data.movies
                        Log.d(TAG," UN Sorted List: ".plus(unsortedMovies))
                        val sortedList  = unsortedMovies?.sortWith(compareBy({it.rating}, {it.rating}))
                        Log.d(TAG," Sorted List: ".plus(sortedList))

                    }

                }
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        val searchView = SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.action_search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                for (section in sectionedAdapter.copyOfSectionsMap.values) {
                    if (section is FilterableSection) {
                        (section as FilterableSection).filter(query)
                    }
                }
                sectionedAdapter.notifyDataSetChanged()

                return true
            }
        })
        searchView.setOnClickListener {view ->  }
    }


}