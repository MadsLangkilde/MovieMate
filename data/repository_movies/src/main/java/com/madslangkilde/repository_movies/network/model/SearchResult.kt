package com.madslangkilde.repository_movies.network.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Search") val searchResults: List<MovieResult>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
)