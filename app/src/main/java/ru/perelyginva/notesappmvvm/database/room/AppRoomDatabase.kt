package ru.perelyginva.notesappmvvm.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.perelyginva.notesappmvvm.R
import ru.perelyginva.notesappmvvm.database.room.dao.NoteRoomDao
import ru.perelyginva.notesappmvvm.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class AppRoomDatabase(): RoomDatabase() {

    abstract fun getRoomDao(): NoteRoomDao

    companion object{
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase{
            return if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    context.getString(R.string.note_database)).build()
                INSTANCE as AppRoomDatabase
            } else{
                    INSTANCE as AppRoomDatabase
            }
        }
    }
}