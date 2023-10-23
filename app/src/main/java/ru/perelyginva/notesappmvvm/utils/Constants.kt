package ru.perelyginva.notesappmvvm.utils

import ru.perelyginva.notesappmvvm.database.DatabaseRepository

const val TYPE_DATABASE = "type_database"
const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

lateinit var REPOSITORY: DatabaseRepository

object Keys {
    const val ID = "id"
}


object Screens {
    const val START_SCREEN = "start_screen"
    const val MAIN_SCREEN = "main_screen"
    const val ADD_SCREEN = "add_screen"
    const val NOTE_SCREEN = "note_screen"
}