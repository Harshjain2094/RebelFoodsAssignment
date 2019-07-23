package com.harsh.rebelfoodsassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.harsh.rebelfoodsassignment.data.local.converters.UsersConverters
import com.harsh.rebelfoodsassignment.data.local.dao.UsersDao
import com.harsh.rebelfoodsassignment.data.local.entity.UsersEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        UsersEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(UsersConverters::class)
abstract class DatabaseService : RoomDatabase() {

    abstract fun usersDao(): UsersDao
}