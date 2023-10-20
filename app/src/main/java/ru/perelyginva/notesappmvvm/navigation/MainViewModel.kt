package ru.perelyginva.notesappmvvm.navigation

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.perelyginva.notesappmvvm.model.NoteModel
import ru.perelyginva.notesappmvvm.utils.TYPE_FIREBASE
import ru.perelyginva.notesappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readTest: MutableLiveData<List<NoteModel>> by lazy {
        MutableLiveData<List<NoteModel>>()
    }
    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value =
            when (dbType.value) {
                TYPE_ROOM -> {
                    listOf<NoteModel>(
                        NoteModel(title = "Note 1", subtitle = "Subtitle for note 1"),
                        NoteModel(title = "Note 2", subtitle = "Subtitle for note 2"),
                        NoteModel(title = "Note 3", subtitle = "Subtitle for note 3"),
                        NoteModel(title = "Note 4", subtitle = "Subtitle for note 4")
                    )
                }

                TYPE_FIREBASE -> {
                    listOf()
                }

                else -> {
                    listOf()
                }
            }
    }

    fun initDatabase(type: String) {
        dbType.value = type
        Log.d("checkData", "MainViewModel init Database with type $type")
    }
}

