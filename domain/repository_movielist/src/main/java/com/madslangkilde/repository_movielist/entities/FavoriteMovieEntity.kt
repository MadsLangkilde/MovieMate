package com.madslangkilde.repository_movielist.entities

data class FavoriteMovieEntity(
    var id: String,
    var movieId: String = "",
    var title: String = "",
    var genre: String = "",
    var year: String = "",
    var imdbID: String = "",
    var runtime: String = "",
    var imdbRating: String = "",
    var type: String = "",
    var poster: String = "",
)