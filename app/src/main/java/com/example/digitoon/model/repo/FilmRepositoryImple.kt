package com.example.digitoon.model.repo

import FilmDataSource
import FilmLocalDataSource
import com.example.digitoon.common.isInternetReachable

import com.example.digitoon.model.Film
import io.reactivex.Completable
import io.reactivex.Single

class FilmRepositoryImple(
    val remoteDataSource: FilmDataSource,
    val localDataSource: FilmLocalDataSource
) : FilmRepository {

    override fun getFilms(): Single<List<Film>> {

        return if (isInternetReachable())
            remoteDataSource.getFilms().
                   doOnSuccess{ films -> insertFilms(films) }
        else
            localDataSource.getFilms()

    }

    override fun insertFilms(films: List<Film>): Completable =
        localDataSource.insertFilms(films)

}