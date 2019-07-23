package com.harsh.rebelfoodsassignment.di.component

import com.harsh.rebelfoodsassignment.di.ActivityScope
import com.harsh.rebelfoodsassignment.di.module.ActivityModule
import com.harsh.rebelfoodsassignment.ui.details.MapsActivity
import com.harsh.rebelfoodsassignment.ui.home.HomeActivity
import com.harsh.rebelfoodsassignment.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: HomeActivity)

    fun inject(activity: SplashActivity)

    fun inject(activity: MapsActivity)

}