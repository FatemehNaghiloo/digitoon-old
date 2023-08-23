package com.example.digitoon.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.digitoon.common.MyViewModel
import com.example.digitoon.model.Film
import com.example.digitoon.model.repo.FilmRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FilmViewModel(filmRepository: FilmRepository) : MyViewModel() {

    val filmsLiveData = MutableLiveData<List<Film>>()

    init {
        progressBarLiveData.value = true
        filmRepository.getFilms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : SingleObserver<List<Film>> {
                
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onSuccess(t: List<Film>) {
                    filmsLiveData.value = t
                }

                override fun onError(e: Throwable) {
                    Log.i("FilmViewModel", e.toString())
                }

            })

    }

}