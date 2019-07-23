package com.harsh.rebelfoodsassignment.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.harsh.rebelfoodsassignment.MyApplication
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.remote.NetworkService
import com.harsh.rebelfoodsassignment.data.repository.UserRepository
import com.harsh.rebelfoodsassignment.di.ApplicationContext
import com.harsh.rebelfoodsassignment.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: MyApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getUserRepository(): UserRepository
}