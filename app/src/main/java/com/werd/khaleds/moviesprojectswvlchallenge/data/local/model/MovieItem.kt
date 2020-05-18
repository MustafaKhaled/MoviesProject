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
) : Parcelable{

}