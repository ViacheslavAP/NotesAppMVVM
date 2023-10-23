package ru.perelyginva.notesappmvvm.navigation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.perelyginva.notesappmvvm.database.room.AppRoomDatabase
import ru.perelyginva.notesappmvvm.database.room.reposotory.RoomRepository
import ru.perelyginva.notesappmvvm.model.NoteModel
import ru.perelyginva.notesappmvvm.utils.REPOSITORY
import ru.perelyginva.notesappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {

        Log.d("checkData", "MainViewModel init Database with type $type")

        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }

    fun addNote(note: NoteModel, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.createNote(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun readAllNotes() = REPOSITORY.readAll
}

