package com.example.digitoon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.digitoon.common.MyViewModel
import com.example.digitoon.model.Detail
import com.example.digitoon.model.repo.DetailRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(imdbId: String, detailRepository: DetailRepository) : MyViewModel() {

    val detailLiveData = MutableLiveData<Detail>()

    init {
        progressBarLiveData.value = true
        detailRepository.getDetail(imdbId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : SingleObserver<Detail> {
                
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: Detail) {
                    detailLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.i("DetailViewModel", e.toString())
                }

            })

    }

}