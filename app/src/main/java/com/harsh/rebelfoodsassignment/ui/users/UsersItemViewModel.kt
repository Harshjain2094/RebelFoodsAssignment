package com.harsh.rebelfoodsassignment.ui.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.local.entity.UsersEntity
import com.harsh.rebelfoodsassignment.data.repository.UserRepository
import com.harsh.rebelfoodsassignment.di.ViewModelScope
import com.harsh.rebelfoodsassignment.ui.details.MapsActivity
import com.harsh.rebelfoodsassignment.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync
import javax.inject.Inject

@ViewModelScope
class UsersItemViewModel @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository,
    private val databaseService: DatabaseService
    ) {

    var usersEntity: UsersEntity? = null
    var context: Context? = null
    val isFav: ObservableField<Boolean> = ObservableField()

    fun updateData(usersEntity: UsersEntity) {
        this.usersEntity = usersEntity
        isFav.set(usersEntity.isFavorite)
    }

    fun onFavClick() {
        usersEntity?.let {
            it.isFavorite = !it.isFavorite
            doAsync { databaseService.usersDao().update(it) }
            updateData(it)
        }
    }

    fun onDetailsClick() {
        context?.let {
            val intent = Intent(it, MapsActivity::class.java)
            intent.putExtra("address", usersEntity?.address)
            it.startActivity(intent)
        }
    }
}