package com.madslangkilde.database_provider.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class FavoriteMovie() : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var movieId: String = ""
    var title: String = ""
    var genre: String = ""
    var year: String = ""
    var imdbID: String = ""
    var imdbRating: String = ""
    var runtime: String = ""
    var type: String = ""
    var poster: String = ""
}