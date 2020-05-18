package com.werd.khaleds.moviesprojectswvlchallenge.ui.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.component.DaggerDataComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.DaggerPresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MoviesSharedViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.ui.MainActivity
import com.werd.khaleds.moviesprojectswvlchallenge.ui.search.di.component.DaggerSearchComponent
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import javax.inject.Inject


class SearchFragment: Fragment() {
    val TAG = javaClass.simpleName
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MoviesSharedViewModel
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
        viewModel = activity?.let { ViewModelProvider(it, viewModelFactory).get(MoviesSharedViewModel::class.java) }!!
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
        val result = viewModel.readMovies().value
        if(result is Results.Success){
            Log.d(TAG," UN Sorted List: ".plus(result.data.movies))
//                        val sortedList  = unsortedMovies?.sortWith(compareBy({it.rating}, {it.rating}))
//                        Log.d(TAG," Sorted List: ".plus(sortedList))

        }
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