package com.madslangkilde.repository_movies.network

import com.madslangkilde.repository_movies.network.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {
    @GET(".")
    suspend fun searchAll(@Query("apiKey") apiKey: String, @Query("s") query: String): SearchResult
}