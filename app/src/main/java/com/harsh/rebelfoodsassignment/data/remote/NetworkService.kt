package com.harsh.rebelfoodsassignment.data.remote

import com.harsh.rebelfoodsassignment.data.remote.response.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET(Endpoints.USERS)
    fun doUsersCall(): Single<List<UsersResponse>>
}