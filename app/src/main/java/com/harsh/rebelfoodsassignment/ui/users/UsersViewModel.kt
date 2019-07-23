package com.harsh.rebelfoodsassignment.ui.users

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.local.entity.UsersEntity
import com.harsh.rebelfoodsassignment.data.repository.UserRepository
import com.harsh.rebelfoodsassignment.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class UsersViewModel (
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository,
    private val databaseService: DatabaseService
) : ViewModel() {

    val users: MutableLiveData<List<UsersEntity>> = MutableLiveData()
    var listVisibility = ObservableInt(View.GONE)

    fun setData(users: List<UsersEntity>) {
        listVisibility.set(if (users.isNullOrEmpty()) View.VISIBLE else View.GONE)
    }
}