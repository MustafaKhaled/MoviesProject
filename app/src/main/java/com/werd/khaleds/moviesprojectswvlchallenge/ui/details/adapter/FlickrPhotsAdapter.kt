package com.werd.khaleds.moviesprojectswvlchallenge.ui.details.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.werd.khaleds.moviesprojectswvlchallenge.BuildConfig
import com.werd.khaleds.moviesprojectswvlchallenge.R
import com.werd.khaleds.moviesprojectswvlchallenge.data.local.model.MovieItem
import com.werd.khaleds.moviesprojectswvlchallenge.data.remote.entities.Photo
import kotlinx.android.synthetic.main.flickr_list_item.view.*

class FlickrPhotsAdapter constructor(): RecyclerView.Adapter<FlickrPhotsAdapter.FlickrPhotoViewHolder>() {
    var mainList: ArrayList<Photo> = ArrayList()
    class FlickrPhotoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val TAG = javaClass.simpleName
        fun bind(item: Photo?) = with(itemView){
            Picasso.get().load(item?.let { formattedPhotoUrl(it) }).into(flickrImg)
        }
        private fun formattedPhotoUrl(photo: Photo): String{
            return BuildConfig.Flickr_URL
            .replace("{farm-id}",photo.farm.toString())
            .replace("{server-id}",photo.server.toString())
            .replace("{id}",photo.id.toString())
            .replace("{secret}",photo.secret.toString())
//            Log.d(TAG,"Formatted Url: ".plus(url))
        }
    }

    fun addAll(flickrPhotosResponse: List<Photo>?){
        flickrPhotosResponse?.let { mainList.addAll(it) }
        flickrPhotosResponse?.size?.let { notifyItemRangeInserted(mainList.size, it) }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrPhotoViewHolder {
        return FlickrPhotoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flickr_list_item, parent, false))
    }

    override fun getItemCount(): Int {
       return mainList.size
    }

    override fun onBindViewHolder(holder: FlickrPhotoViewHolder, position: Int) {
        holder.bind(mainList.get(holder.adapterPosition))
    }


}