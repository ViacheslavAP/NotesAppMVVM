package ru.perelyginva.notesappmvvm.database.room.reposotory

import androidx.lifecycle.LiveData
import ru.perelyginva.notesappmvvm.database.DatabaseRepository
import ru.perelyginva.notesappmvvm.database.room.dao.NoteRoomDao
import ru.perelyginva.notesappmvvm.model.NoteModel

class RoomRepository(private val noteRoomDao: NoteRoomDao): DatabaseRepository {
    override val readAll: LiveData<List<NoteModel>>
        get() = noteRoomDao.getAllNotes()

    override suspend fun createNote(note: NoteModel, onSuccess: () -> Unit) {
        noteRoomDao.addNote(note = note)
        onSuccess()
    }

    override suspend fun updateNote(note: NoteModel, onSuccess: () -> Unit) {
        noteRoomDao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun deleteNote(note: NoteModel, onSuccess: () -> Unit) {
        noteRoomDao.deleteNote(note = note)
        onSuccess()
    }


}