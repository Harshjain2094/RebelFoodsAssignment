package com.harsh.rebelfoodsassignment.data.repository

import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.local.entity.UsersEntity
import com.harsh.rebelfoodsassignment.data.remote.NetworkService
import io.reactivex.Single
import org.jetbrains.anko.doAsync
import javax.inject.Inject

class UserRepository @Inject constructor(
    val networkService: NetworkService,
    val databaseService: DatabaseService
) {


    fun saveDataToLocal(entity: UsersEntity) {
        doAsync {
            databaseService.usersDao().insert(entity)
        }
    }

    fun doUserFetch(): Single<List<UsersEntity>> =
        networkService.doUsersCall()
            .map {
                it.map {
                    UsersEntity(
                        it.id,
                        it.name,
                        it.username,
                        it.email,
                        it.address,
                        it.phone,
                        it.website,
                        it.company,
                        false
                    )
                }
            }
}