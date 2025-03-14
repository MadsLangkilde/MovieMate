package com.madslangkilde.database_provider.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class NoteCommand() : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var noteId: String? = null
    var textPosition: Int? = null
    var addedText: String? = null
    var removedText: String? = null
}