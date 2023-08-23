package com.example.digitoon.tech

import com.example.digitoon.model.Detail
import com.example.digitoon.model.Film
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?apikey=3e974fca&s=batman")
    fun getFilms(): Single<List<Film>>

    @GET("?apikey=3e974fca&i={imdbID}")
    fun getDetail(@Query("imdbID") imdbId: String): Single<Detail>
 
}

fun createApiServiceInstance(): ApiService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}