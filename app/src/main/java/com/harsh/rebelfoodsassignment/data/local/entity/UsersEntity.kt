package com.harsh.rebelfoodsassignment.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.harsh.rebelfoodsassignment.data.remote.response.AddressResponse
import com.harsh.rebelfoodsassignment.data.remote.response.Company
import org.jetbrains.annotations.NotNull

@Entity(tableName = "users_entity")
data class UsersEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NotNull
    val id: String,

    @ColumnInfo(name = "name")
    @NotNull
    val name: String,


    @ColumnInfo(name = "username")
    val username: String,


    @ColumnInfo(name = "email")
    val email: String,


    @ColumnInfo(name = "address")
    val address: AddressResponse,


    @ColumnInfo(name = "phone")
    val phone: String,


    @ColumnInfo(name = "website")
    val website: String,


    @ColumnInfo(name = "company")
    val company: Company,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)