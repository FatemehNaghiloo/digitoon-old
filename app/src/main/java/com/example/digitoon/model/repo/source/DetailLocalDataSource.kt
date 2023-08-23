package com.example.digitoon.model.repo.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.digitoon.model.Detail
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface DetailLocalDataSource : DetailDataSource {

    @Query("SELECT * FROM detail")
    override fun getDetail(id: String): Single<Detail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertDetail(detail: Detail): Completable

}