package com.madslangkilde.repository_movielist.entities


data class NoteCommandEntity(
    var id: String,
    var noteId: String?,
    var textPosition: Int? = null,
    var addedText: String? = null,
    var removedText: String? = null
)