package com.harsh.rebelfoodsassignment

import android.app.Application
import com.harsh.rebelfoodsassignment.di.component.ApplicationComponent
import com.harsh.rebelfoodsassignment.di.component.DaggerApplicationComponent
import com.harsh.rebelfoodsassignment.di.module.ApplicationModule

class MyApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}