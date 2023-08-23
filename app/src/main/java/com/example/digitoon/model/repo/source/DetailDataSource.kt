package com.example.digitoon.model.repo.source

import com.example.digitoon.model.Detail
import io.reactivex.Completable
import io.reactivex.Single

interface DetailDataSource {

    fun getDetail(id: String): Single<Detail>

    fun insertDetail(detail: Detail): Completable

}