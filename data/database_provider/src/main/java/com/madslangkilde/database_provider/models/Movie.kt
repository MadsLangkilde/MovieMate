package com.madslangkilde.database_provider.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class Movie() : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var title: String = ""
    var genre: String = ""
    var type: String = ""
    var imdbRating: String = ""
    var year: String = ""
    var imdbID: String = ""
    var runtime: String = ""
    var poster: String = ""
    var foundWithQuery: String? = ""
}