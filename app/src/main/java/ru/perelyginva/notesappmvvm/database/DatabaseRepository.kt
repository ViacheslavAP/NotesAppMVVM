package ru.perelyginva.notesappmvvm.database

import androidx.lifecycle.LiveData
import ru.perelyginva.notesappmvvm.model.NoteModel

interface DatabaseRepository {

    val readAll: LiveData<List<NoteModel>>

    suspend fun createNote(note:NoteModel, onSuccess: () -> Unit)

    suspend fun updateNote(note:NoteModel, onSuccess: () -> Unit)

    suspend fun deleteNote(note:NoteModel, onSuccess: () -> Unit)
}