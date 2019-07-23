package com.harsh.rebelfoodsassignment.di.module

import androidx.recyclerview.widget.RecyclerView
import com.harsh.rebelfoodsassignment.utils.rx.RxSchedulerProvider
import com.harsh.rebelfoodsassignment.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ViewHolderModule(private val viewHolder: RecyclerView.ViewHolder) {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()
}