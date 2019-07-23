package com.harsh.rebelfoodsassignment.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.repository.UserRepository
import com.harsh.rebelfoodsassignment.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository,
    private val databaseService: DatabaseService
) : ViewModel() {
    init {
    }

    val isResetting: MutableLiveData<Boolean> = MutableLiveData()
    val messageString: MutableLiveData<String> = MutableLiveData()

    fun reset() {
        isResetting.postValue(true)
        compositeDisposable.addAll(
            userRepository.doUserFetch()
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    it.forEach {
                        userRepository.saveDataToLocal(it)
                        isResetting.postValue(false)
                    }
                }, {
                    messageString.postValue(it.message)
                    isResetting.postValue(false)
                })
        )
    }
}