package com.werd.khaleds.moviesprojectswvlchallenge.ui.master

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem

class AllMoviesAdapter(private val clickListener: (MovieItem) -> Unit): RecyclerView.Adapter<AllMoviesAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class MovieViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(movieItem: MovieItem, clickListener: (MovieItem) -> Unit) = with(itemView){

            itemView.setOnClickListener {
                clickListener(movieItem)
            }
        }
    }
}