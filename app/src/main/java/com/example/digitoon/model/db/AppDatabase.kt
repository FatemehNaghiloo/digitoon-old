package com.example.digitoon.model.db

import FilmLocalDataSource
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.digitoon.model.Detail
import com.example.digitoon.model.Film
import com.example.digitoon.model.repo.source.DetailLocalDataSource

@Database(entities = [Film::class, Detail::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmLocalDataSource

    abstract fun detailDao(): DetailLocalDataSource

}