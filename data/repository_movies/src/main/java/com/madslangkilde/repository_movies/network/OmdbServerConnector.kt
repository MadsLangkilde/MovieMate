package com.madslangkilde.repository_movies.network

import com.madslangkilde.repository_base_server.BaseServerConnector
import com.madslangkilde.repository_movies.network.model.SearchResult

class OmdbServerConnector(private val apiKey: String, private val baseUrl: String) : BaseServerConnector<OmdbApi>() {
    override fun getBaseUrl()  = baseUrl
    override fun getClazz(): Class<OmdbApi> = OmdbApi::class.java
    suspend fun search(query: String): SearchResult {
        val r = api!!.searchAll(apiKey, query)
        return r
    }
}