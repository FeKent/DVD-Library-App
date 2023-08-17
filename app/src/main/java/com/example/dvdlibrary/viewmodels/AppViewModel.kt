package com.example.dvdlibrary.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.dvdlibrary.data.DvdAppDatabase
import com.example.dvdlibrary.data.Film
import kotlinx.coroutines.flow.Flow


class AppViewModel : ViewModel() {
    private fun database(context: Context): DvdAppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            DvdAppDatabase::class.java,
            "database-name"
        ).build()
    }

    fun films(context: Context): Flow<List<Film>> {
       return database(context).filmsDao().allFilms()
    }

    suspend fun deleteFilm(context: Context, film: Film){
        return database(context).filmsDao().delete(film)
    }

}