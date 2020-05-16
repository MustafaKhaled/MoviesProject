package com.werd.khaleds.moviesprojectswvlchallenge.ui.search

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication
import com.werd.khaleds.moviesprojectswvlchallenge.MyApplication.Companion.appContext
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.data.di.component.DaggerDataComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.di.component.DaggerPresentationComponent
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.factory.ViewModelFactory
import com.werd.khaleds.moviesprojectswvlchallenge.presentation.viewmodel.MovieDetailsViewModel
import com.werd.khaleds.moviesprojectswvlchallenge.ui.MainActivity
import com.werd.khaleds.moviesprojectswvlchallenge.ui.search.di.component.DaggerSearchComponent
import javax.inject.Inject


class SearchFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: MovieDetailsViewModel
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
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater?.inflate(R.menu.search_menu, menu)
        val searchView = SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.action_search).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        searchView.setOnClickListener {view ->  }
    }


}