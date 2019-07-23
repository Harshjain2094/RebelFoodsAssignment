package com.harsh.rebelfoodsassignment.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.harsh.rebelfoodsassignment.MyApplication
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.remote.Endpoints
import com.harsh.rebelfoodsassignment.data.remote.NetworkService
import com.harsh.rebelfoodsassignment.data.remote.Networking
import com.harsh.rebelfoodsassignment.di.ApplicationContext
import com.harsh.rebelfoodsassignment.utils.rx.RxSchedulerProvider
import com.harsh.rebelfoodsassignment.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "assignment-db"
        ).build()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            Endpoints.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )
}