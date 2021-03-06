package com.example.everytranslation.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.everytranslation.db.dto.User

@Dao
interface UserDao {
    @Query("SELECT * FROM USER WHERE userId == :userId")
    fun get(userId : Long) : User

    @Query("SELECT * FROM USER WHERE userId == :userId")
    fun getLiveData(userId : Int) : LiveData<User>

    @Query("SELECT * FROM USER")
    fun getAll() : List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM USER")
    fun deleteAll()
}