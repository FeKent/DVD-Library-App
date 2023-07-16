package com.example.dvdlibrary.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Film::class], version = 1)
abstract class DvdAppDatabase : RoomDatabase() {
    abstract fun filmsDao(): FilmsDao
}