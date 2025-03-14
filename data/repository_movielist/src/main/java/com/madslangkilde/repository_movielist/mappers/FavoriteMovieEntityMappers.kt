package com.madslangkilde.repository_movielist.mappers

import com.madslangkilde.database_provider.models.FavoriteMovie
import com.madslangkilde.database_provider.models.MovieNote
import com.madslangkilde.database_provider.models.NoteCommand
import com.madslangkilde.repository_movielist.entities.FavoriteMovieEntity
import com.madslangkilde.repository_movielist.entities.MovieNoteEntity
import com.madslangkilde.repository_movielist.entities.NoteCommandEntity
import io.realm.kotlin.ext.realmListOf
import java.util.UUID

fun List<FavoriteMovieEntity>.toFavoriteMovieEntities(): List<FavoriteMovie> {
    return this.map { it.toFavoriteMovieEntity() }
}

fun FavoriteMovieEntity.toFavoriteMovieEntity(): FavoriteMovie {
    val entity = this
    return FavoriteMovie().apply {
        id = entity.id ?: UUID.randomUUID().toString()
        movieId = entity.movieId
        title = entity.title
        genre = entity.genre
        year = entity.year
        imdbID = entity.imdbID
        runtime = entity.runtime
        imdbRating = entity.imdbRating
        type = entity.type
        poster = entity.poster
    }
}

fun MovieNoteEntity.toMovieNoteEntity(): MovieNote {
    val entity = this
    return MovieNote().apply {
        id = entity.id
        note = entity.note
        undos = realmListOf<NoteCommand>().apply { entity.undos.map { it.toNoteCommand() } }
        redos = realmListOf<NoteCommand>().apply { entity.redos.map { it.toNoteCommand() } }
    }
}

fun NoteCommandEntity.toNoteCommand(): NoteCommand {
    val entity = this
    return NoteCommand().apply {
        id = entity.id
        noteId = entity.noteId
        textPosition = entity.textPosition
        addedText = entity.addedText
        removedText = entity.removedText
    }
}