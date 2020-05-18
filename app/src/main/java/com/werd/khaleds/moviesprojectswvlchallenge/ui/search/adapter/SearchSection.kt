package com.werd.khaleds.moviesprojectswvlchallenge.ui.search.adapter

import android.text.TextUtils
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MoviesLocalResult
import com.werd.khaleds.moviesprojectswvlchallenge.ui.search.FilterableSection
import io.github.luizgrp.sectionedrecyclerviewadapter.Section
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import kotlinx.android.synthetic.main.all_movies_list_item.view.*
import kotlinx.android.synthetic.main.header_item_search.view.*
import kotlinx.android.synthetic.main.movie_item_search.view.*

import java.util.*
import kotlin.collections.ArrayList


internal class SearchSection(
    private var moviesList: ArrayList<MovieItem>,
    title: Int
) : FilterableSection, Section(
        SectionParameters.builder()
            .itemResourceId(R.layout.movie_item_search)
            .headerResourceId(R.layout.header_item_search)
            .build()
    ) {
    private var filteredList: ArrayList<MovieItem> = ArrayList(moviesList)
    private var list: ArrayList<MovieItem>
    private var title: Int = 0
     init {
         this.list = filteredList
         this.title = title
     }

    override fun getContentItemsTotal(): Int {
        return filteredList.size
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val itemHolder: ItemViewHolder = holder as ItemViewHolder
        val movieItem: MovieItem = filteredList[position]

        itemHolder.bind(movieItem)
    }

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder) {
        val headerHolder: HeaderViewHolder = holder as HeaderViewHolder
        headerHolder.bind(title)
    }

    override fun filter(query: String?) {
        if (TextUtils.isEmpty(query)) {
            filteredList.clear()
            filteredList.addAll(moviesList)
            this.isVisible = true
        } else {
            filteredList.clear();
            for (movie in moviesList) {
            if (query?.toLowerCase(Locale.getDefault())?.let {
                    movie.title?.toLowerCase(Locale.getDefault())
                        ?.contains(it)
                }!! && filteredList.size<5) {
                filteredList.add(movie);
            }
        }

            this.isVisible = filteredList.isNotEmpty();
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(title: Int) = with(itemView) {
            yearTitle.text = title.toString()
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieItem: MovieItem) = with(itemView) {
            searchTitle.text = movieItem.title
            if(movieItem.rating!=null)  searchRatingBar.rating = movieItem.rating.toFloat()        }

    }



}