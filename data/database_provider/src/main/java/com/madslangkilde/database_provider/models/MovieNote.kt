package com.madslangkilde.database_provider.models

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class MovieNote() : RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    var movieId: String = ""
    var note: String = ""
    var undos: RealmList<NoteCommand> = realmListOf()
    var redos: RealmList<NoteCommand> = realmListOf()
}