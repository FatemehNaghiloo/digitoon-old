package com.example.digitoon

import FilmRemoteDataSource
import android.app.Application

import androidx.room.Room
import com.example.digitoon.model.db.AppDatabase
import com.example.digitoon.model.repo.DetailRepository
import com.example.digitoon.model.repo.DetailRepositoryImple
import com.example.digitoon.model.repo.FilmRepository
import com.example.digitoon.model.repo.FilmRepositoryImple
import com.example.digitoon.model.repo.source.DetailRemoteDataSource
import com.example.digitoon.tech.FrescoImageLoadingService
import com.example.digitoon.tech.ImageLoadingService
import com.example.digitoon.tech.createApiServiceInstance
import com.example.digitoon.view.FilmListAdapter
import com.example.digitoon.viewmodel.DetailViewModel
import com.example.digitoon.viewmodel.FilmViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import io.reactivex.schedulers.Schedulers.single
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        val myModules = module {
            single { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingService() }
            single { Room.databaseBuilder(this@App, AppDatabase::class.java, "db_app").build() }
            factory<FilmRepository> {
                FilmRepositoryImple(
                    FilmRemoteDataSource(get()),
                    get<AppDatabase>().filmDao()
                )
            }
            factory { FilmListAdapter(get()) }
            viewModel { FilmViewModel(get()) }
            factory<DetailRepository> {
                DetailRepositoryImple(
                    DetailRemoteDataSource(get()),
                    get<AppDatabase>().detailDao()
                )
            }
            viewModel { (imdbId: String) -> DetailViewModel(imdbId, get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}