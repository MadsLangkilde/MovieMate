package com.madslangkilde.repository_movielist.mappers

import com.madslangkilde.database_provider.models.FavoriteMovie
import com.madslangkilde.database_provider.models.MovieNote
import com.madslangkilde.database_provider.models.NoteCommand
import com.madslangkilde.repository_movielist.entities.FavoriteMovieEntity
import com.madslangkilde.repository_movielist.entities.MovieNoteEntity
import com.madslangkilde.repository_movielist.entities.NoteCommandEntity

fun List<FavoriteMovie>.toFavoriteMovieEntities(): List<FavoriteMovieEntity> {
    return this.map { it.toFavoriteMovieEntity() }
}

fun FavoriteMovie.toFavoriteMovieEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        id = id,
        movieId = movieId,
        title = title,
        year = year,
        imdbID = imdbID,
        type = type,
        poster = poster
    )
}

fun MovieNote.toMovieNoteEntity(): MovieNoteEntity {
    return MovieNoteEntity(
        id = id,
        note = note,
        undos = undos.map { it.toNoteCommand() },
        redos = redos.map { it.toNoteCommand() }
    )
}

fun NoteCommand.toNoteCommand(): NoteCommandEntity {
    return NoteCommandEntity(
        id = id,
        noteId = noteId,
        textPosition = textPosition,
        addedText = addedText,
        removedText = removedText
    )
}