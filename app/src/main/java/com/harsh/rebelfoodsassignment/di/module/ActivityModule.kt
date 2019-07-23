package com.harsh.rebelfoodsassignment.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.repository.UserRepository
import com.harsh.rebelfoodsassignment.ui.details.MapsViewModel
import com.harsh.rebelfoodsassignment.ui.home.HomeViewModel
import com.harsh.rebelfoodsassignment.ui.home.UserPagerAdapter
import com.harsh.rebelfoodsassignment.ui.splash.SplashViewModel
import com.harsh.rebelfoodsassignment.utils.ViewModelProviderFactory
import com.harsh.rebelfoodsassignment.utils.rx.RxSchedulerProvider
import com.harsh.rebelfoodsassignment.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private var activity: AppCompatActivity) {

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideActivity(): AppCompatActivity = activity

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    fun provideFragmentManager(): FragmentManager = activity.supportFragmentManager

    @Provides
    fun providesUsersPagerAdapter() : UserPagerAdapter = UserPagerAdapter(activity, activity.supportFragmentManager)

    @Provides
    fun provideUserViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        userRepository: UserRepository,
        databaseService: DatabaseService
    ): HomeViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(schedulerProvider, compositeDisposable, userRepository, databaseService)
        }
    ).get(HomeViewModel::class.java)

    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        userRepository: UserRepository,
        databaseService: DatabaseService
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(SplashViewModel::class) {
            SplashViewModel(compositeDisposable, schedulerProvider, userRepository, databaseService)
        }
    ).get(SplashViewModel::class.java)


    @Provides
    fun provideMapViewModel(): MapsViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MapsViewModel::class) {
            MapsViewModel()
        }
    ).get(MapsViewModel::class.java)
}