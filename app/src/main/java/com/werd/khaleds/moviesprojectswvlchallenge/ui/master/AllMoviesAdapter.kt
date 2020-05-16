package com.werd.khaleds.moviesprojectswvlchallenge.ui.master

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import kotlinx.android.synthetic.main.all_movies_list_item.view.*

class AllMoviesAdapter(private val clickListener: (MovieItem) -> Unit): RecyclerView.Adapter<AllMoviesAdapter.MovieViewHolder>() {
    private val mainList = ArrayList<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.all_movies_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(mainList[holder.adapterPosition],clickListener)
    }

    fun addAll(moviesList: ArrayList<MovieItem>?){
        moviesList?.let { mainList.addAll(it) }
        moviesList?.size?.let { notifyItemRangeInserted(mainList.size, it) }
    }

    class MovieViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(movieItem: MovieItem, clickListener: (MovieItem) -> Unit) = with(itemView){
            title.text = movieItem.title
            year.text = movieItem.year.toString()
            if(movieItem.rating!=null)ratingBar.rating = movieItem.rating.toFloat()
            itemView.setOnClickListener {
                clickListener(movieItem)
            }
        }
    }
}