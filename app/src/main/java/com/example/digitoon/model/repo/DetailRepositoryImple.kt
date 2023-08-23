package com.example.digitoon.model.repo

import com.example.digitoon.common.isInternetReachable
import com.example.digitoon.model.Detail
import com.example.digitoon.model.repo.source.DetailDataSource
import com.example.digitoon.model.repo.source.DetailLocalDataSource
import io.reactivex.Completable
import io.reactivex.Single

class DetailRepositoryImple(
    val remoteDataSource: DetailDataSource,
    val localDataSource: DetailLocalDataSource
) : DetailRepository {

    override fun getDetail(id: String): Single<Detail> {

        return if (isInternetReachable())
            remoteDataSource.getDetail(id).
                  doOnSuccess { detail -> insertDetail(detail) }

        else
            localDataSource.getDetail(id)

    }

    override fun insertDetail(detail: Detail): Completable =
        localDataSource.insertDetail(detail)
    
}