package com.werd.khaleds.moviesprojectswvlchallenge.ui.search

import androidx.annotation.NonNull

internal interface FilterableSection {
    fun filter(@NonNull query: String?)
}