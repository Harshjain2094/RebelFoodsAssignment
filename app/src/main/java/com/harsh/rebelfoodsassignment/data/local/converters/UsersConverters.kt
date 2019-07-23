package com.harsh.rebelfoodsassignment.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.harsh.rebelfoodsassignment.data.remote.response.AddressResponse
import com.harsh.rebelfoodsassignment.data.remote.response.Company

class UsersConverters {

    @TypeConverter
    fun fromAddress(address: AddressResponse): String {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun toAddress(address: String): AddressResponse {
        val addressType = object : TypeToken<AddressResponse>() {
        }.type
        return Gson().fromJson(address, addressType)
    }

    @TypeConverter
    fun fromCompany(company: Company): String {
        return Gson().toJson(company)
    }

    @TypeConverter
    fun toCompany(company: String): Company {
        val addressType = object : TypeToken<Company>() {
        }.type
        return Gson().fromJson(company, addressType)
    }

}