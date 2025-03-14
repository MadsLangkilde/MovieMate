package com.madslangkilde.database_provider

import com.madslangkilde.database_provider.models.FavoriteMovie
import com.madslangkilde.database_provider.models.Movie
import com.madslangkilde.database_provider.models.MovieNote
import com.madslangkilde.database_provider.models.NoteCommand
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class DatabaseProvider {
    companion object {
        private const val REALM_SCHEMAVERSION = 1
        private const val REALM_DB_NAME = "moviesmatedb.realm"
    }

    private val realmObjects = setOf(Movie::class, MovieNote::class, FavoriteMovie::class, NoteCommand::class)

    private var realmConfig: RealmConfiguration = RealmConfiguration.Builder(schema = realmObjects)
        .schemaVersion(REALM_SCHEMAVERSION.toLong())
        .name(REALM_DB_NAME)
        .build()

    val realm: Realm = Realm.open(realmConfig)
}