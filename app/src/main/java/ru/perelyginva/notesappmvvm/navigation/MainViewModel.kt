package ru.perelyginva.notesappmvvm.navigation

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.perelyginva.notesappmvvm.database.room.AppRoomDatabase
import ru.perelyginva.notesappmvvm.database.room.reposotory.RoomRepository
import ru.perelyginva.notesappmvvm.model.NoteModel
import ru.perelyginva.notesappmvvm.utils.REPOSITORY
import ru.perelyginva.notesappmvvm.utils.TYPE_FIREBASE
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
}

