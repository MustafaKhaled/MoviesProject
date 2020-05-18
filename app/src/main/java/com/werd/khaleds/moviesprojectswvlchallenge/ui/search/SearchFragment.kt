package com.werd.khaleds.moviesprojectswvlchallenge.ui.search

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.component.DaggerDataComponent
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.DaggerPresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MoviesSharedViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.ui.MainActivity
import com.werd.khaleds.moviesprojectswvlchallenge.ui.search.di.component.DaggerSearchComponent
import com.werd.khaleds.moviesprojectswvlchallenge.util.Results
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import java.util.*
import javax.inject.Inject
import kotlin.Comparator
import kotlin.collections.ArrayList


class SearchFragment : Fragment() {
    val TAG = javaClass.simpleName
    private val sortedList = ArrayList<MovieItem>()

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
                            .applicationComponent(MyApplication.applicationComponent).build()
                    )
                    .build()
            ).build().inject(this)
        viewModel = activity?.let {
            ViewModelProvider(
                it,
                viewModelFactory
            ).get(MoviesSharedViewModel::class.java)
        }!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sectionedAdapter = SectionedRecyclerViewAdapter()
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result = viewModel.readMovies().value
        if (result is Results.Success) {
            val moviesList = result.data.movies
            sortList(moviesList)


        }
    }

    private fun sortList(moviesList: ArrayList<MovieItem>) {
        Log.d(TAG, "Unsorted List: ".plus(moviesList))
        //Sorting the movies by Top-rated with complexity nLog(n)
        moviesList.sortWith(Comparator { obj1, obj2 ->
            obj2?.rating?.compareTo(obj1?.rating!!)!! // To compare rate values
        })
        //Add list after sorting
        sortedList.addAll(moviesList)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu)
        val searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
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
        searchView.setOnClickListener { view -> }
    }


}