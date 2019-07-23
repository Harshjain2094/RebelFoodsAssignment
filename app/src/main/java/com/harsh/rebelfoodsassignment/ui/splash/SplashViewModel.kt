package com.harsh.rebelfoodsassignment.ui.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.repository.UserRepository
import com.harsh.rebelfoodsassignment.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SplashViewModel (
    private val compositeDisposable: CompositeDisposable,
    private val schedulerProvider: SchedulerProvider,
    private val userRepository: UserRepository,
    private val databaseService: DatabaseService
) : ViewModel() {

    val messageString: MutableLiveData<String> = MutableLiveData()

    val launchHome: MutableLiveData<Bundle> = MutableLiveData()

    fun onViewCreated() {

        doAsync {
            Log.d("Size", "${databaseService.usersDao().getCount()} ::")
            if (databaseService.usersDao().getCount() <= 0) {
                uiThread {
                    compositeDisposable.addAll(
                        userRepository.doUserFetch()
                            .subscribeOn(schedulerProvider.io())
                            .subscribe({
                                it.forEach {
                                    userRepository.saveDataToLocal(it)
                                    launchHome.postValue(Bundle())
                                }
                            }, {
                                messageString.postValue(it.message)
                                launchHome.postValue(Bundle())
                            })
                    )
                }
            } else {
                uiThread {
                    Handler().postDelayed({
                        launchHome.postValue(Bundle())
                    }, 500)
                }
            }
        }
    }
}