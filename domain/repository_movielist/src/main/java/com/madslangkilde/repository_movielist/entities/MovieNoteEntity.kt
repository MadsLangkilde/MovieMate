package com.madslangkilde.repository_movielist.entities


data class MovieNoteEntity(
    var id: String,
    var note: String = "",
    var undos: List<NoteCommandEntity> = emptyList(),
    var redos: List<NoteCommandEntity> = emptyList()
)