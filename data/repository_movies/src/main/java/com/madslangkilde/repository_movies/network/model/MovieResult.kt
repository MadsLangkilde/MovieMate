package com.madslangkilde.repository_movies.network.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Genre") val genre: String?,
    @SerializedName("imdbRating") val imdbRating: String?,
    @SerializedName("imdbID") val imdbID: String?,
    @SerializedName("Type") val type: String?,
    @SerializedName("Runtime") val runtime: String?,
    @SerializedName("Poster") val poster: String?,
)
