package com.example.digitoon.model.repo.source

import com.example.digitoon.model.Detail
import com.example.digitoon.tech.ApiService
import io.reactivex.Completable
import io.reactivex.Single

class DetailRemoteDataSource(private val apiService: ApiService) : DetailDataSource {

    override fun getDetail(id:String): Single<Detail> = apiService.getDetail(id)

    override fun insertDetail(detail: Detail): Completable {
        TODO("Not yet implemented")
    }

}