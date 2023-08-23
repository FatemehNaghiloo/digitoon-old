package com.example.digitoon.model.repo

import com.example.digitoon.model.Film
import io.reactivex.Completable
import io.reactivex.Single

interface FilmRepository {

    fun getFilms(): Single<List<Film>>

    fun insertFilms(films: List<Film>): Completable

}