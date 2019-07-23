package com.harsh.rebelfoodsassignment.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.harsh.rebelfoodsassignment.data.local.entity.UsersEntity


@Dao
interface UsersDao {

    @Query("SELECT * FROM users_entity")
    fun getAll(): LiveData<List<UsersEntity>>

    @Query("SELECT COUNT(*) FROM users_entity")
    fun getCount(): Int

    @Query("SELECT * FROM users_entity WHERE isFavorite = 1")
    fun getFavList(): LiveData<List<UsersEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: UsersEntity)

    @Update
    fun update(entity: UsersEntity)

    @Delete
    fun delete(entity: UsersEntity)

    @Query("DELETE FROM users_entity")
    fun deleteAll()
}