package com.harsh.rebelfoodsassignment.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("username")
    val username: String,

    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("address")
    val address: AddressResponse,

    @Expose
    @SerializedName("phone")
    val phone: String,

    @Expose
    @SerializedName("website")
    val website: String,

    @Expose
    @SerializedName("company")
    val company: Company
)

data class Company(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("catchPhrase")
    val catchPhrase: String,

    @Expose
    @SerializedName("bs")
    val bs: String
)