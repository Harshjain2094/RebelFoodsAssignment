package com.harsh.rebelfoodsassignment.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.repository.UserRepository
import com.harsh.rebelfoodsassignment.ui.home.HomeViewModel
import com.harsh.rebelfoodsassignment.ui.users.UsersAdapter
import com.harsh.rebelfoodsassignment.ui.users.UsersViewModel
import com.harsh.rebelfoodsassignment.utils.ViewModelProviderFactory
import com.harsh.rebelfoodsassignment.utils.rx.RxSchedulerProvider
import com.harsh.rebelfoodsassignment.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideFragment(): Fragment = fragment

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun providePostAdapter() = UsersAdapter(ArrayList())

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        userRepository: UserRepository,
        databaseService: DatabaseService
    ): UsersViewModel =
            ViewModelProviders.of(fragment,
                ViewModelProviderFactory(UsersViewModel::class) {
                    UsersViewModel(schedulerProvider, compositeDisposable, userRepository, databaseService )
                }).get(UsersViewModel::class.java)


}