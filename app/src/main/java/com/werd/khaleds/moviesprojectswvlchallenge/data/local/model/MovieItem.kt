package com.werd.khaleds.moviesprojectswvlchallenge.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(
    val cast: List<String>?,
    val genres: List<String>?,
    val rating: Int?,
    val title: String?,
    val year: Int?
) : Parcelable, Comparator<MovieItem> {
    override fun compare(o1: MovieItem?, o2: MovieItem?): Int {
        return o1?.rating!!.compareTo(o2?.rating!!)
    }

}