package com.harsh.rebelfoodsassignment.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressResponse(
    @Expose
    @SerializedName("street")
    val street: String,

    @Expose
    @SerializedName("suite")
    val suite: String,

    @Expose
    @SerializedName("city")
    val city: String,

    @Expose
    @SerializedName("zipcode")
    val zipcode: String,

    @Expose
    @SerializedName("geo")
    val geo: Geo
) : Parcelable

@Parcelize
data class Geo(
    @Expose
    @SerializedName("lat")
    val lat: String,

    @Expose
    @SerializedName("lng")
    val lng: String
) : Parcelable